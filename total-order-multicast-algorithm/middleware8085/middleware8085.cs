using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Linq;


public class Program
{
    public static void Main()
    {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(false);
        Application.Run(new Form1());
    }
}

// potential issues (not sure yet):
// 1. the access to shared resoursed (logicClock, two queues, the dictionary,...) should be mutex?
public class Form1 : Form
{
    private Button button1;
    private RichTextBox sentTextBox, receivedTextBox, readyTextBox;
    private Label labelSent, labelReceived, labelReady;
    private TcpListener listener;
    private int sequencenNum = 1;
    private int logicClock = 0;
    private const int PROCESSID = 4;
    // Key: (proposedTimeValue, msgPID, msgSeqenceNumber); Value: (messageContent, isAbleToMoveToDeliveryQueue)
    private SortedDictionary<(int, int, int), (string, bool)> holdingQueue = new SortedDictionary<(int, int, int), (string, bool)>();
    Queue<string> deliverQueue = new Queue<string>();
    private Dictionary<int, List<int>> proposedTimestamps = new Dictionary<int, List<int>>();


    public Form1()
    {
        this.Text = "Middleware 4";
        this.Size = new System.Drawing.Size(670, 350);

        button1 = new Button();
        button1.Size = new System.Drawing.Size(70, 25);
        button1.Location = new System.Drawing.Point(50, 25);
        button1.Text = "Send";
        button1.Click += new EventHandler(button1_Click);

        labelSent = new Label();
        labelSent.Text = "Sent";
        labelSent.Location = new System.Drawing.Point(50, 90);
        sentTextBox = new RichTextBox();
        sentTextBox.Location = new System.Drawing.Point(50, 120);
        sentTextBox.Width = 150;
        sentTextBox.Height = 150;
        sentTextBox.Multiline = true;
        sentTextBox.ScrollBars = RichTextBoxScrollBars.Vertical;

        labelReceived = new Label();
        labelReceived.Text = "Received";
        labelReceived.Location = new System.Drawing.Point(250, 90);
        receivedTextBox = new RichTextBox();
        receivedTextBox.Location = new System.Drawing.Point(250, 120);
        receivedTextBox.Width = 150;
        receivedTextBox.Height = 150;
        receivedTextBox.Multiline = true;
        receivedTextBox.ScrollBars = RichTextBoxScrollBars.Vertical;

        labelReady = new Label();
        labelReady.Text = "Ready";
        labelReady.Location = new System.Drawing.Point(450, 90);
        readyTextBox = new RichTextBox();
        readyTextBox.Location = new System.Drawing.Point(450, 120);
        readyTextBox.Width = 150;
        readyTextBox.Height = 150;
        readyTextBox.Multiline = true;
        readyTextBox.ScrollBars = RichTextBoxScrollBars.Vertical;

        Controls.Add(button1);
        Controls.Add(labelSent);
        Controls.Add(sentTextBox);
        Controls.Add(labelReceived);
        Controls.Add(receivedTextBox);
        Controls.Add(labelReady);
        Controls.Add(readyTextBox);

        listener = new TcpListener(IPAddress.Any, 8085);
        listener.Start();
        ListenForClientsAsync();
    }

    private async void ListenForClientsAsync()
    {
        while (true)
        {
            TcpClient client = await listener.AcceptTcpClientAsync();
            ReadMessageAsync(client);
        }
    }

    private async void ReadMessageAsync(TcpClient client)
    {
        byte[] buffer = new byte[1024];
        await client.GetStream().ReadAsync(buffer, 0, buffer.Length);
        string message = Encoding.UTF8.GetString(buffer).Trim('\0');

        int[] msgInfo = GetIncomingMsgTimestamp(message);
        int msgTV = msgInfo[0];
        int msgPID = msgInfo[1];
        int seqNum = msgInfo[2];

        // multicast message from network program
        if (message.Contains("from Middleware"))
        {
            if (msgPID != PROCESSID)
            {
                int proposedTV;

                // assign proposed TS to the message (only change time value but keep process id the same)            
                logicClock = Math.Max(logicClock, msgTV) + 1;
                proposedTV = logicClock;
                logicClock++;

                // buffer the received messages in the holding queue
                holdingQueue.Add((proposedTV, msgPID, seqNum), (message, false));

                // send proposed TS back to the sender directly 
                string proposedTSControlMsg = $"{seqNum}| proposedTS: ({proposedTV},{msgPID})";
                int receiverPort = 8086 - (5 - msgPID);
                SendProposedTS(proposedTSControlMsg, receiverPort);

                receivedTextBox.AppendText($"{message}: proposedTS ({proposedTV},{msgPID})\n");
            }
            else
            {
                receivedTextBox.AppendText($"{message}: proposedTS ({msgTV},{msgPID})\n");
            }
        }
        // control message directly from other middlewares 
        else
        {
            // 1. first condition (as a multicast sender for control message exchange)
            // wait for all other processes's proposed timestamp
            // choose the largest timestamp as agreed timestamp ($"{seqNum}| agreedTS: ({agreedTV},{PROCESSID})")
            // send the agreed TS back to all other processes directly
            if (message.Contains("proposedTS"))
            {

                proposedTimestamps[seqNum].Add(msgTV);

                if (proposedTimestamps[seqNum].Count == 5)
                {
                    int agreedTS = proposedTimestamps[seqNum].Max();
                    string agreedTSControlMsg = $"{seqNum}| agreedTS: ({agreedTS},{PROCESSID})";
                    SendAgreedTS(agreedTSControlMsg);

                    UpdateHoldingQueue(seqNum, agreedTS, msgPID);
                    UpdateDeliveryQueue();
                    DisplayReadyMessages();

                }
            }
            // 2. second condition (as a receiver)
            // wait for the agreed timestamp
            // update the timestamp of corresponding message in the holding queue
            // if the message is the head after update, move it to delivery queue
            else if (message.Contains("agreedTS"))
            {

                // update the timevalue assigned to the message and the marker to be true
                UpdateHoldingQueue(seqNum, msgTV, msgPID);
                UpdateDeliveryQueue();
                DisplayReadyMessages();

            }
        }
    }

    private async void button1_Click(object sender, EventArgs e)
    {
        TcpClient client = new TcpClient("localhost", 8081);

        int msgTimeValue;

        msgTimeValue = logicClock++;


        int seqNum = sequencenNum++;
        string sentText = $"Msg #{seqNum} from Middleware {PROCESSID} ({msgTimeValue},{PROCESSID})";


        // add the message into holding queue and record it into proposedTimestamps
        holdingQueue.Add((msgTimeValue, PROCESSID, seqNum), (sentText, false));
        List<int> timeValues = new List<int>();
        timeValues.Add(msgTimeValue);
        proposedTimestamps.Add(seqNum, timeValues);


        byte[] data = Encoding.UTF8.GetBytes(sentText);
        await client.GetStream().WriteAsync(data, 0, data.Length);
        sentTextBox.AppendText($"{sentText}\n");
    }

    private int[] GetIncomingMsgTimestamp(string msg)
    {
        string incomingMsgTV = msg.Substring(msg.IndexOf("(") + 1, msg.IndexOf(",") - msg.IndexOf("(") - 1);
        string incomingMsgPID = msg.Substring(msg.IndexOf(",") + 1, msg.IndexOf(")") - msg.IndexOf(",") - 1);
        int msgTV = int.Parse(incomingMsgTV);
        int msgPID = int.Parse(incomingMsgPID);
        string seqNum;
        if (msg.Contains("proposedTS") || msg.Contains("agreedTS"))
        {
            seqNum = msg.Substring(0, msg.IndexOf("|"));
        }
        else
        {
            seqNum = msg.Substring(msg.IndexOf("#") + 1, msg.IndexOf(" from") - msg.IndexOf("#") - 1);

        }
        int seqNumber = int.Parse(seqNum);
        int[] timestamp = new int[] { msgTV, msgPID, seqNumber };
        return timestamp;
    }

    private async void SendProposedTS(string proposedTSControlMsg, int receiverPort)
    {
        TcpClient client = new TcpClient("localhost", receiverPort);
        byte[] data = Encoding.UTF8.GetBytes(proposedTSControlMsg);
        await client.GetStream().WriteAsync(data, 0, data.Length);
    }

    private async void SendAgreedTS(string agreedTSControlMsg)
    {
        int[] ports = new int[] { 8082, 8083, 8084, 8086 };
        for (int i = 0; i < 4; i++)
        {
            TcpClient client = new TcpClient("localhost", ports[i]);
            byte[] data = Encoding.UTF8.GetBytes(agreedTSControlMsg);
            await client.GetStream().WriteAsync(data, 0, data.Length);
        }
    }

    private void UpdateHoldingQueue(int seqNum, int agreedTS, int msgPID)
    {
        foreach (KeyValuePair<(int, int, int), (string, bool)> kvp in holdingQueue)
        {
            var (kTimeValue, kMsgPID, kSeqNum) = kvp.Key;
            if (seqNum == kSeqNum && msgPID == kMsgPID)
            {
                holdingQueue.Remove(kvp.Key);
                var (message, isFinalized) = kvp.Value;
                holdingQueue.Add((agreedTS, kMsgPID, kSeqNum), (message, true));
                break;
            }
        }
    }

    private void UpdateDeliveryQueue()
    {
        List<(int, int, int)> keysToRemove = new List<(int, int, int)>();

        foreach (KeyValuePair<(int, int, int), (string, bool)> kvp in holdingQueue)
        {
            var (proposedTimeValue, msgPID, msgSeqenceNumber) = kvp.Key;
            var (message, isFinalized) = kvp.Value;
            if (isFinalized)
            {
                keysToRemove.Add(kvp.Key);
                deliverQueue.Enqueue($"{message} with final TS: {proposedTimeValue}");
            }
            // if the first message is not finalized, cannot update delivery queue for now
            else
            {
                break;
            }
        }

        foreach (var key in keysToRemove)
        {
            holdingQueue.Remove(key);
        }
    }

    private void DisplayReadyMessages()
    {
        while (deliverQueue.Count > 0)
        {
            string msgDisplay = deliverQueue.Dequeue();
            readyTextBox.Invoke((MethodInvoker)delegate
            {
                readyTextBox.AppendText($"{msgDisplay}\n");
            });
        }
    }
}
