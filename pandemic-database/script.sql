--         table creation script
create table region (
    regionname varchar(10) primary key
    );

create table risklevel (
    districtname varchar(255) primary key,
    risk_level varchar(5) check(risk_level in('high','mid','low')),
    regionname varchar(10) not null,
    constraint fk_risklevel_region foreign key (regionname) references region (regionname)
    );

create table bslocation (
    gpslocation varchar(255) primary key,
    corresponding_district varchar(255) not null,
    constraint fk_basestation_risklevel foreign key (corresponding_district) references risklevel (districtname)
    ); 

create table bsrecord (
    gpslocation varchar(255),
    simid varchar(255),
    connectiontime datetime,
    disconnectiontime datetime,
    constraint pk primary key(simid,connectiontime),
    constraint fk_travelrecord_basestation foreign key (gpslocation) references bslocation (gpslocation)

    );

create table testhospital (
    hospitalname varchar(255) primary key,
    districtname varchar(255) not null,
    constraint fk_testhospital_risklevel foreign key (districtname) references risklevel (districtname)
    );

create table sampletype (
    typename varchar(255) primary key,
    description text not null
    );

create table testpeople (
    mobile varchar(255) PRIMARY key,
    name varchar(255) not null,
    sex varchar(10) not null check(sex in ('Female','Male')),
    age int not null check(age >= 0)
    
    );

create table testdoctor (
    doctorname varchar(255) primary key,    -- if two doctors have the same name, then add some numbers or letters to identify.
    hospitalname varchar(255) not null,
    constraint fk_testdoctor_testhospital foreign key (hospitalname) references testhospital (hospitalname) 
    
    );

create table testreport (
    mobile varchar(255),
    CONSTRAINT fk_testdoctor_testpeople foreign key (mobile) references testpeople (mobile),
    collecttime datetime,
    CONSTRAINT pk primary key (mobile,collecttime),
    typename varchar(255) not null,
    constraint fk_testreport_sampletype foreign key (typename) REFERENCES sampletype (typename),
    doctorname varchar(255),
    CONSTRAINT fk_testreport_testdoctor foreign key (doctorname) REFERENCES testdoctor (doctorname),
    testtime datetime,
    result varchar(10) check(result in('positive','negative')), 
    reporttime datetime
    
    );

--         Important use cases
--  !!! both test data and SELECT statements are needed

-- use case 1
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel (districtname,regionname) values ('a','central'),('b','central'),('c','central');
insert into bslocation values ('1,1','a'),('2,2','a'),('3,3','a'),('5,5','b'),('6,6','b'),('7,7','b'),('9,9','c'),('10,10','c'),('11,11','c');

insert into bsrecord values ('1,1','123450','2021-01-01 00:00:00',null),('2,2','123451','2021-10-01 08:00:00',null),
('2,2','123452','2021-10-01 08:00:00',null),('1,1','123453','2021-10-01 00:00:00',null),('2,2','123454','2021-10-08 11:00:00',null),
('3,3','123455','2021-10-06 15:00:00',null),('3,3','233636','2021-01-01 00:00:00',null),('5,5','123456','2021-09-28 08:00:00',null),
('11,11','123457','2021-10-01 09:00:00','2021-10-08 09:00:00'),('7,7','123457','2021-10-08 09:00:00',null);

select t.simid from 
(select r.simid,r.connectiontime,r.disconnectiontime,l.gpslocation from bslocation as l,bsrecord as r WHERE
l.corresponding_district in
(select l.corresponding_district from bsrecord as r inner join bslocation as l ON
r.gpslocation = l.gpslocation and r.simid = '233636') and l.gpslocation = r.gpslocation) as t
where
(t.connectiontime > '2021-10-07 19:30:00' or 
t.disconnectiontime is null OR
t.disconnectiontime > '2021-10-07 19:30:00') and t.simid <> '233636';

-- use case 2
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel (districtname,regionname) values ('a','central'),('b','central'),('c','central');
insert into bslocation values ('1,1','a'),('2,2','a'),('3,3','a'),('5,5','b'),('6,6','b'),('7,7','b'),('9,9','c'),('10,10','c'),('11,11','c');

insert into bsrecord values ('2,2','233636','2021-01-01 00:00:00',null);
update bsrecord set disconnectiontime = '2021-10-01 08:00:00' where simid = 233636 and connectiontime = '2021-01-01 00:00:00';
insert into bsrecord values ('3,3','233636','2021-10-01 08:00:00',null);
update bsrecord set disconnectiontime = '2021-10-01 09:00:00' where simid = 233636 and connectiontime = '2021-10-01 08:00:00';
insert into bsrecord values ('2,2','233636','2021-10-01 09:00:00',null);

select l.corresponding_district,r.gpslocation,r.connectiontime,r.disconnectiontime from bsrecord as r, bslocation as l
WHERE r.simid = '233636' and l.gpslocation = r.gpslocation;

-- use case 3
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel (districtname,regionname) values ('a','central'),('b','central'),('c','central'),('d','central');
insert into testhospital values ('aa','a'),('bb','b'),('cc','c'),('dd','d');

insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');

insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc'),
('Dave','dd'),('Duck','dd'),('Dick','dd');

insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15);

insert into testreport values 
('000001','2021-12-1 08:00:00','Coughid-21','Alen','2021-12-1 10:22:00','negative','2021-12-1 13:11:00'),
('000002','2021-12-1 09:00:00','Coughid-21','Alen','2021-12-1 11:00:00','negative','2021-12-1 14:00:00'),
('000003','2021-12-1 19:43:00','Coughid-21','Amy','2021-12-1 21:00:00',null,null),
('000004','2021-12-1 15:55:00','Coughid-21','Betty','2021-12-1 17:21:00','negative','2021-12-1 19:33:00'),
('000005','2021-12-1 11:52:00','Coughid-21','Bob','2021-12-1 14:00:00','negative','2021-12-1 16:43:00'),
('000006','2021-12-1 08:32:00','Coughid-21','Cindy','2021-12-1 10:00:00','negative','2021-12-1 11:17:00'),
('000007','2021-12-1 16:59:00','Coughid-21','Curry','2021-12-1 19:00:00','negative','2021-12-1 21:19:00'),
('000008','2021-12-1 21:12:00','Coughid-21','Curry','2021-12-2 08:11:00','negative','2021-12-2 10:00:00'),
('000009','2021-12-1 09:45:00','Coughid-21','Cindy',null,null,null),
('000010','2021-12-1 10:12:00','Coughid-21','Dave','2021-12-1 12:00:00','negative','2021-12-1 14:53:00'),
('000011','2021-12-1 13:51:00','Coughid-21','Dave','2021-12-1 15:29:00','negative','2021-12-1 18:00:00'),
('000012','2021-12-1 15:21:00','Coughid-21','Dick','2021-12-1 17:09:00','negative','2021-12-1 19:00:00');

select hospitalname from
(select hospitalname,AVG(doctor_averagetime) as hospital_averagetime from 
testdoctor natural join 
(SELECT tr.doctorname, AVG(tr.reporttime - tr.testtime) as doctor_averagetime from testreport as tr 
where tr.testtime is not null and tr.reporttime is not null group by tr.doctorname) as t1
group by hospitalname 
having hospital_averagetime <= ALL
(select AVG(doctor_averagetime) from testdoctor natural join 
(SELECT tr.doctorname, AVG(tr.reporttime - tr.testtime) as doctor_averagetime from testreport as tr 
where tr.testtime is not null and tr.reporttime is not null group by tr.doctorname) as t1))
as t2;

-- use case 4
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel values ('a','low','central'),('b','low','central'),('c','low','central'),('d','low','central'),('e','low','east'),('f','low','east'),
('g','low','east'),('h','low','east'),('i','low','west'),('j','low','west'),('k','low','west'),('l','low','west'),('m','low','north'),('n','low','north'),('o','low','north'),('p','low','north'),
('q','low','south'),('r','low','south'),('s','low','south'),('t','low','south');

insert into testhospital values ('aa','a'),('bb','b'),('cc','c'),('dd','d'),('ee','e'),('ff','f'),('gg','h'),('hh','h'),('ii','i'),('jj','j'),('kk','k'),('ll','l');

insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc'),
('Dave','dd'),('Duck','dd'),('Dick','dd');

insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');

insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15),('000013','Richard','Male',19),('000014','Mask','Male','55'),('000015','Yong','Male',23),
('000016','Catt','Female',13),('000017','Tesla','Male',76),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);

insert into testreport values 
('000001','2021-12-1 08:00:00','Coughid-21','Alen','2021-12-1 10:22:00','negative','2021-12-1 13:11:00'),
('000002','2021-12-1 09:00:00','Coughid-21','Alen','2021-12-1 11:00:00','negative','2021-12-1 14:00:00'),
('000003','2021-12-1 19:43:00','Coughid-21','Amy','2021-12-1 21:00:00','negative','2021-12-2 08:00:00'),
('000004','2021-12-1 15:55:00','Coughid-21','Betty','2021-12-1 17:21:00','negative','2021-12-1 19:33:00'),
('000005','2021-12-1 11:52:00','Coughid-21','Bob','2021-12-1 14:00:00','negative','2021-12-1 16:43:00'),
('000006','2021-12-1 08:32:00','Coughid-21','Cindy','2021-12-1 10:00:00','negative','2021-12-1 11:17:00'),
('000007','2021-12-1 16:59:00','Coughid-21','Curry','2021-12-1 19:00:00','negative','2021-12-1 21:19:00'),
('000008','2021-12-1 21:12:00','Coughid-21','Curry','2021-12-2 08:11:00','negative','2021-12-2 10:00:00'),
('000009','2021-12-1 09:45:00','Coughid-21','Cindy','2021-12-1 11:11:00','negative','2021-12-1 15:00:00'),
('000010','2021-12-1 10:12:00','Coughid-21','Dave','2021-12-1 12:00:00','negative','2021-12-1 14:53:00'),
('000011','2021-12-1 13:51:00','Coughid-21','Dave','2021-12-1 15:29:00','negative','2021-12-1 18:00:00'),
('000012','2021-12-1 15:21:00','Coughid-21','Dick','2021-12-1 17:09:00','negative','2021-12-1 19:00:00'),
('000013','2021-10-3 08:00:00','Coughid-21','Bob','2021-10-3 10:00:00','negative','2021-10-3 13:00:00'),
('000013','2021-10-4 10:00:00','Coughid-21','Bob','2021-10-4 11:56:00','negative','2021-10-4 14:00:00'),
('000014','2021-10-3 11:09:00','Coughid-21','Cindy','2021-10-3 13:50:00','negative','2021-10-3 16:40:00'),
('000014','2021-10-4 08:28:00','Coughid-21','Cindy','2021-10-4 10:50:00','negative','2021-10-4 13:44:00'),
('000015','2021-10-4 10:00:00','Coughid-21','Dick','2021-10-4 12:00:00','negative','2021-10-4 14:00:00'),
('000016','2021-10-3 11:00:00','Coughid-21','Curry','2021-10-3 13:00:00','negative','2021-10-3 15:46:00'),
('000017','2021-10-4 12:00:00','Coughid-21','Curry','2021-10-4 15:00:00','negative','2021-10-4 18:26:00'),
('000018','2021-10-3 11:00:00','Coughid-21','Curry','2021-10-3 13:00:00','negative','2021-10-3 15:46:00'),
('000019','2021-10-3 11:00:59','Coughid-21','Curry','2021-10-3 13:05:00','negative','2021-10-3 15:59:00'),
('000020','2021-10-3 11:01:47','Coughid-21','Curry','2021-10-3 13:19:00','negative','2021-10-3 16:46:00');

select mobile from
(select mobile,MAX(collecttime)-MIN(collecttime) as gaptime from 
(select mobile,collecttime from testreport
where mobile in 
(select t.mobile from 
(select count(mobile) as testtimes, mobile, collecttime from testreport
group by mobile) as t
where testtimes >= 2)) as t1
where collecttime >= '2021-10-3 00:00:00' and collecttime <= '2021-10-5 00:00:00'
group by mobile
having gaptime >= 1000000) as t2;   

-- use case 5
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel values ('Bunny Tail district','low','central'),('Raspberry town','low','east'),('Lenny town','high','north'),
('Centre Lukewarm Hillside','high','south'),
('Glow Sand district','mid','west');

select districtname as district_name,risk_level from risklevel
order by FIELD(risk_level,'high','mid','low');

-- use case 6
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel (districtname,regionname) values ('Centre Lukewarm Hillside','central'),('Lenny town','north');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');
insert into testhospital values ('aa','Centre Lukewarm Hillside'),('bb','Centre Lukewarm Hillside'),('cc','Centre Lukewarm Hillside'),('dd','Lenny town'),
('ee','Lenny town');
insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);

insert into testreport values 
('000001','2021-10-4 08:00:00','Coughid-21','Alen','2021-10-4 10:22:00','negative','2021-10-4 13:11:00'),
('000002','2021-10-4 09:00:00','Coughid-21','Alen','2021-10-4 11:00:00','negative','2021-10-4 14:00:00'),
('000003','2021-10-4 19:43:00','Coughid-21','Amy','2021-10-4 21:00:00','negative','2021-10-5 08:00:00'),
('000004','2021-10-4 15:55:00','Coughid-21','Betty','2021-10-4 17:21:00','positive','2021-10-4 19:33:00'),
('000005','2021-10-4 11:52:00','Coughid-21','Bob','2021-10-4 14:00:00','positive','2021-10-4 16:43:00'),
('000006','2021-10-4 08:32:00','Coughid-21','Cindy','2021-10-4 10:00:00','positive','2021-10-4 11:17:00'),
('000007','2021-10-4 16:59:00','Coughid-21','Curry','2021-10-4 19:00:00','negative','2021-10-4 21:19:00'),
('000008','2021-10-4 21:12:00','Coughid-21','Curry','2021-10-5 08:11:00','negative','2021-10-4 10:00:00'),
('000009','2021-10-4 09:45:00','Coughid-21','Cindy','2021-10-4 11:00:00','negative','2021-10-4 14:34:00'),
('000010','2021-10-4 10:12:00','Coughid-21','Alen','2021-10-4 12:00:00','negative','2021-10-4 14:53:00'),
('000018','2021-10-3 11:00:00','Coughid-21','Curry','2021-10-3 13:00:00','negative','2021-10-3 15:46:00'),
('000019','2021-10-3 11:00:59','Coughid-21','Curry','2021-10-3 13:05:00','negative','2021-10-3 15:59:00'),
('000020','2021-10-3 11:01:47','Coughid-21','Curry','2021-10-3 13:19:00','negative','2021-10-3 16:46:00');

select name,mobile from 
testdoctor natural join 
(select name,r.mobile,doctorname from testpeople natural join testreport as r where 
(r.collecttime >= '2021-10-4 00:00:00' and r.collecttime < '2021-10-5 00:00:00') and
 r.result = 'positive') as t1 
where
hospitalname in (select hospitalname from testhospital
where districtname = 'Centre Lukewarm Hillside')
;

-- use case 7
insert into region values ('north'),('south'),('east'),('west'),('central');
insert into risklevel (districtname,regionname) values ('Centre Lukewarm Hillside','central'),('Lenny town','north');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');
insert into testhospital values ('aa','Centre Lukewarm Hillside'),('bb','Centre Lukewarm Hillside'),('cc','Centre Lukewarm Hillside'),('dd','Lenny town'),
('ee','Lenny town');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc');
insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15),('000013','Richard','Male',19),('000014','Mask','Male','55'),('000015','Yong','Male',23),
('000016','Catt','Female',13),('000017','Tesla','Male',76),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);

insert into testreport values 
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 09:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 14:00:00'),
('000003','2021-10-04 19:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 08:00:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Betty','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Bob','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-10-04 08:32:00','Coughid-21','Cindy','2021-10-04 10:00:00','negative','2021-10-04 11:17:00'),
('000007','2021-10-04 16:59:00','Coughid-21','Curry','2021-10-04 19:00:00','negative','2021-10-04 21:19:00'),
('000008','2021-10-04 21:12:00','Coughid-21','Curry','2021-10-05 08:11:00','positive','2021-10-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Cindy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 10:12:00','Coughid-21','Curry','2021-10-04 12:00:00','negative','2021-10-04 14:53:00'),
('000011','2021-10-05 13:51:00','Coughid-21','Alen','2021-10-05 15:29:00','negative','2021-10-05 18:00:00'),
('000012','2021-10-05 15:21:00','Coughid-21','Alen','2021-10-05 17:09:00','positive','2021-10-05 19:00:00'),
('000013','2021-10-05 10:00:00','Coughid-21','Bob','2021-10-05 11:56:00','negative','2021-10-05 14:00:00'),
('000014','2021-10-05 11:09:00','Coughid-21','Cindy','2021-10-05 13:50:00','positive','2021-10-05 16:40:00'),
('000015','2021-10-05 10:00:00','Coughid-21','Betty','2021-10-05 12:00:00','negative','2021-10-05 14:00:00'),
('000016','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','positive','2021-10-05 15:46:00'),
('000017','2021-10-05 12:00:00','Coughid-21','Curry','2021-10-05 15:00:00','negative','2021-10-05 18:26:00'),
('000018','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','negative','2021-10-05 15:46:00'),
('000019','2021-10-05 11:00:59','Coughid-21','Curry','2021-10-05 13:05:00','negative','2021-10-05 15:59:00'),
('000020','2021-10-05 11:01:47','Coughid-21','Curry','2021-10-05 13:19:00','negative','2021-10-05 16:46:00');

select (date5positive - date4positive) as increment from 
(select * from (select count(mobile) as date5positive from testreport WHERE
(collecttime >= '2021-10-5 00:00:00' and collecttime < '2021-10-6 00:00:00') and result = 'positive'
and doctorname in (select doctorname from testhospital natural join testdoctor 
where districtname = 'Centre Lukewarm Hillside')) as t1 natural join 
(select count(mobile) as date4positive from testreport WHERE
(collecttime >= '2021-10-4 00:00:00' and collecttime < '2021-10-5 00:00:00') and result = 'positive'
and doctorname in (select doctorname from testhospital natural join testdoctor 
where districtname = 'Centre Lukewarm Hillside')) as t2) as t3;

-- use case 8
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname,regionname) values ('a','central'),('b','central'),('c','central');
insert into bslocation values ('1,1','a'),('2,2','a'),('3,3','a'),('5,5','b'),('6,6','b'),('7,7','b'),('9,9','c'),('10,10','c'),('11,11','c');
insert into bsrecord values ('1,1','123450','2021-01-01 00:00:00',null),('2,2','123451','2021-10-01 08:00:00',null),
('2,2','123452','2021-10-01 08:00:00',null),('1,1','123453','2021-10-01 00:00:00',null),('2,2','123454','2021-10-08 11:00:00',null),
('3,3','123455','2021-10-06 15:00:00',null),('3,3','233636','2021-01-01 00:00:00',null);
insert into testhospital values ('aa','a'),('bb','b'),('cc','c');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');
insert into testpeople values ('123450','James','Male',36),('123451','Mina','Female',23),('123452','Durant','Male',33),('123453','Jason','Male',54),
('123454','Cindy','Female','16'),('123455','Morant','Male','45');
insert into testreport values ('123450','2021-10-11 08:00:00','Coughid-21','Alen','2021-10-11 10:00:00','negative','2021-10-11 14:00:00'),
('123451','2021-10-14 09:00:00','Coughid-21','Amy','2021-10-14 11:00:00','positive','2021-10-14 15:00:00'),
('123452','2021-10-16 10:00:00','Coughid-21','Bob','2021-10-16 12:00:00','positive','2021-10-16 15:00:00'),
('123453','2021-10-18 13:00:00','Coughid-21','Betty','2021-10-18 15:00:00','negative','2021-10-18 17:00:00'),
('123454','2021-10-19 09:00:00','Coughid-21','Curry','2021-10-19 11:00:00','negative','2021-10-19 15:00:00'),
('123455','2021-10-13 15:00:00','Coughid-21','Curry','2021-10-13 16:00:00','negative','2021-10-13 19:00:00'),
;


select totalnumber / infectednumber as spreadrate from 
(select count(t1.simid) as totalnumber from
(select t.simid from 
(select r.simid,r.connectiontime,r.disconnectiontime,l.gpslocation from bslocation as l,bsrecord as r WHERE
l.corresponding_district in
(select l.corresponding_district from bsrecord as r inner join bslocation as l ON
r.gpslocation = l.gpslocation and r.simid = '233636') and l.gpslocation = r.gpslocation) as t
where
(t.connectiontime > '2021-10-07 19:30:00' or 
t.disconnectiontime is null OR
t.disconnectiontime > '2021-10-07 19:30:00')) as t1) as tn1,
(select COUNT(mobile) as infectednumber from 
(select * from testreport inner join 
(select t.simid from 
(select r.simid,r.connectiontime,r.disconnectiontime,l.gpslocation from bslocation as l,bsrecord as r WHERE
l.corresponding_district in
(select l.corresponding_district from bsrecord as r inner join bslocation as l ON
r.gpslocation = l.gpslocation and r.simid = '233636') and l.gpslocation = r.gpslocation) as t
where
(t.connectiontime > '2021-10-07 19:30:00' or 
t.disconnectiontime is null OR
t.disconnectiontime > '2021-10-07 19:30:00')) as t1 ON
testreport.mobile = simid) as t2 WHERE
result = 'positive' and collecttime >= '2021-10-09 19:30:00' and collecttime <= '2021-10-23 19:30:00') as tn2;


--          Extended use cases
--  !!! both test data and SELECT statements are needed

-- use case 1
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname,regionname) values ('a','central'),('b','central'),('c','central');
insert into testhospital values ('aa','a'),('bb','a'),('cc','a');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc');
insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('233636','Mark','Male',19);
insert into testreport values 
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 09:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 14:00:00'),
('000003','2021-10-04 19:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 08:00:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Betty','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('233636','2021-09-08 12:00:00','Coughid-21','Amy','2021-09-08 13:00:00','negative','2021-09-08 15:00:00'),
('233636','2021-10-04 10:00:00','Coughid-21','Betty','2021-10-04 13:00:00','negative','2021-10-04 16:00:00');

select tp.name,tr.collecttime,tr.result from testreport as tr natural join testpeople as tp 
where
mobile = '233636' and collecttime >= '2021-10-04 00:00:00';

-- use case 2
insert into region values ('central','north','south','east','west');
insert into risklevel values ('a','low','central'),('b','low','central'),('c','mid','north'),('d','low','north'),('e','mid','south'),('f','low','south'),
('g','high','east'),('h','high','east'),('i','low','west'),('j','low','west');

select districtname from risklevel WHERE risk_level = 'low';

-- use case 3
insert into region values ('central','north','south','east','west');
insert into risklevel values ('a','low','central'),('b','low','central'),('c','mid','north'),('d','low','north'),('e','mid','south'),('f','low','south'),
('g','high','east'),('h','high','east'),('i','low','west'),('j','low','west');
insert into bslocation values ('1,1','a'),('2,2','a'),('3,3','b'),('4,4','b'),('5,5','c'),('6,6','c'),('7,7','d'),('8,8','d'),('9,9','e'),('10,10','e'),('11,11','f'),('12,12','f'),
('13,13','g'),('14,14','g'),('15,15','h'),('16,16','h'),('17,17','i'),('18,18','i'),('19,19','j'),('20,20','j');
insert into bsrecord values ('5,5','123450','2021-10-02 00:00:00',null),('7,7','123451','2021-10-03 12:00:00',null),
('1,1','233636','2021-09-26 08:00:00','2021-10-2 09:00:00'),('15,15','233636','2021-10-2 09:00:00','2021-10-05 11:00:00'),
('3,3','233636','2021-10-05 11:00:00','2021-10-07 09:00:00'),('1,1','233636','2021-10-07 09:00:00',null);

select districtname from 
(select gpslocation from bsrecord where simid = '233636' and connectiontime >= '2021-10-01 00:00:00') as t1 
natural join 
(select gpslocation, r.districtname from risklevel r inner JOIN bslocation l on r.districtname = l.corresponding_district where 
risk_level = 'high' or risk_level = 'mid') as t2; 

-- use case 4
insert into region values ('central','north','south','east','west');
insert into risklevel values ('a','low','central'),('b','low','central');
insert into testhospital values ('aa','a'),('bb','a'),('cc','a'),('dd','b');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc'),
('Dave','dd'),('Dick','dd'),('Duck','dd');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');
insert into testpeople values ('000001','James','Male',9),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',11),('000009','Harden','Male',74),
('000010','Coco','Female',11);
insert into testreport values 
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 09:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 14:00:00'),
('000003','2021-10-04 19:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 08:00:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Betty','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Bob','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-10-04 08:32:00','Coughid-21','Cindy','2021-10-04 10:00:00','negative','2021-10-04 11:17:00'),
('000007','2021-10-04 16:59:00','Coughid-21','Curry','2021-10-04 19:00:00','negative','2021-10-04 21:19:00'),
('000008','2021-10-04 21:12:00','Coughid-21','Curry','2021-10-05 08:11:00','negative','2021-10-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Cindy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 10:12:00','Coughid-21','Curry','2021-10-04 12:00:00','negative','2021-10-04 14:53:00');

select name,mobile from 
testdoctor natural join 
(select name,r.mobile,doctorname from testpeople natural join testreport as r where 
r.collecttime >= '2021-10-1 00:00:00'  and age >= 12 and age <= 70 and
r.result = 'negative') as t1 
where
hospitalname in (select hospitalname from testhospital
where districtname = 'a')
;

-- use case 5
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');

insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15),('000013','Richard','Male',19),('000014','Mask','Male',55),('000015','Yong','Male',23),
('000016','Catt','Female',13),('000017','Tesla','Male',76),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);

insert into testhospital values ('aa','a'),('bb','a'),('cc','b'),('dd','b'),('ee','c'),('ff','d'),('gg','e'),('hh','f'),('ii','g'),('jj','h'),('kk','i'),('ll','j');

insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','bb'),('Bob','bb'),('Billy','cc'),('Betty','cc'),('Cathy','dd'),('Cindy','ee'),('Curry','ff'),
('Dave','gg'),('Dick','hh'),('Duck','ii'),('Kevein','jj'),('Newton','kk'),('Kart','ll');

insert into testreport values 
('000001','2020-10-04 08:00:00','Coughid-21','Alen','2020-10-04 10:22:00','negative','2020-10-04 13:11:00'),
('000002','2020-10-04 09:00:00','Coughid-21','Alen','2020-10-04 11:00:00','negative','2020-10-04 14:00:00'),
('000003','2020-10-04 19:43:00','Coughid-21','Amy','2020-10-04 21:00:00','negative','2020-10-04 08:00:00'),
('000004','2020-10-04 15:55:00','Coughid-21','Betty','2020-10-04 17:21:00','negative','2020-10-04 19:33:00'),
('000005','2020-10-04 11:52:00','Coughid-21','Bob','2020-10-04 14:00:00','negative','2020-10-04 16:43:00'),
('000006','2020-10-04 08:32:00','Coughid-21','Cindy','2020-10-04 10:00:00','negative','2020-10-04 11:17:00'),
('000007','2020-10-04 16:59:00','Coughid-21','Curry','2020-10-04 19:00:00','negative','2020-10-04 21:19:00'),
('000008','2020-10-04 21:12:00','Coughid-21','Curry','2020-10-05 08:11:00','positive','2020-10-05 10:00:00'),
('000009','2020-10-04 09:45:00','Coughid-21','Cindy','2020-10-04 11:11:00','negative','2020-10-04 15:00:00'),
('000010','2020-10-04 10:12:00','Coughid-21','Curry','2020-10-04 12:00:00','negative','2020-10-04 14:53:00'),
('000011','2020-10-05 13:51:00','Coughid-21','Alen','2020-10-05 15:29:00','negative','2020-10-05 18:00:00'),
('000012','2020-10-05 15:21:00','Coughid-21','Alen','2020-10-05 17:09:00','positive','2020-10-05 19:00:00'),
('000013','2020-10-05 10:00:00','Coughid-21','Bob','2020-10-05 11:56:00','negative','2020-10-05 14:00:00'),
('000014','2020-10-05 11:09:00','Coughid-21','Cindy','2020-10-05 13:50:00','positive','2020-10-05 16:40:00'),
('000015','2020-10-05 10:00:00','Coughid-21','Betty','2020-10-05 12:00:00','negative','2020-10-05 14:00:00'),
('000016','2020-10-05 11:00:00','Coughid-21','Curry','2020-10-05 13:00:00','positive','2020-10-05 15:46:00'),
('000017','2020-10-05 12:00:00','Coughid-21','Curry','2020-10-05 15:00:00','negative','2020-10-05 18:26:00'),
('000018','2020-10-05 11:00:00','Coughid-21','Curry','2020-10-05 13:00:00','negative','2020-10-05 15:46:00'),
('000019','2020-10-05 11:00:59','Coughid-21','Curry','2020-10-05 13:05:00','negative','2020-10-05 15:59:00'),
('000020','2020-10-05 11:01:47','Coughid-21','Curry','2020-10-05 13:19:00','negative','2020-10-05 16:46:00'),
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 09:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 14:00:00'),
('000003','2021-10-04 19:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 08:00:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Betty','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Bob','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-10-04 08:32:00','Coughid-21','Cindy','2021-10-04 10:00:00','negative','2021-10-04 11:17:00'),
('000007','2021-10-04 16:59:00','Coughid-21','Curry','2021-10-04 19:00:00','negative','2021-10-04 21:19:00'),
('000008','2021-10-04 21:12:00','Coughid-21','Curry','2021-10-05 08:11:00','negative','2021-10-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Cindy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 10:12:00','Coughid-21','Curry','2021-10-04 12:00:00','negative','2021-10-04 14:53:00'),
('000011','2021-10-05 13:51:00','Coughid-21','Alen','2021-10-05 15:29:00','negative','2021-10-05 18:00:00'),
('000012','2021-10-05 15:21:00','Coughid-21','Alen','2021-10-05 17:09:00','negative','2021-10-05 19:00:00'),
('000013','2021-10-05 10:00:00','Coughid-21','Bob','2021-10-05 11:56:00','negative','2021-10-05 14:00:00'),
('000014','2021-10-05 11:09:00','Coughid-21','Cindy','2021-10-05 13:50:00','positive','2021-10-05 16:40:00'),
('000015','2021-10-05 10:00:00','Coughid-21','Betty','2021-10-05 12:00:00','negative','2021-10-05 14:00:00'),
('000016','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','negative','2021-10-05 15:46:00'),
('000017','2021-10-05 12:00:00','Coughid-21','Curry','2021-10-05 15:00:00','negative','2021-10-05 18:26:00'),
('000018','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','negative','2021-10-05 15:46:00'),
('000019','2021-10-05 11:00:59','Coughid-21','Curry','2021-10-05 13:05:00','negative','2021-10-05 15:59:00'),
('000020','2021-10-05 11:01:47','Coughid-21','Curry','2021-10-05 13:19:00','negative','2021-10-05 16:46:00');

select (pc2021 - pc2020) as changenumber from
(select count(mobile) as pc2021 from
(select mobile from testreport where 
result = 'positive' and collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') as t2) as t3,
(select count(mobile) as pc2020 from
(select mobile from testreport where 
result = 'positive' and collecttime >= '2020-01-01 00:00:00' and collecttime < '2021-01-01 00:00:00') as t1) as t4;

-- use case 6
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');
insert into testhospital values ('aa','a'),('bb','a'),('cc','b'),('dd','b'),('ee','c'),('ff','d'),('gg','e'),('hh','f'),('ii','g'),('jj','h'),('kk','i'),('ll','j');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','bb'),('Bob','bb'),('Billy','cc'),('Betty','cc'),('Cathy','dd'),('Cindy','ee'),('Curry','ff'),
('Dave','gg'),('Dick','hh'),('Duck','ii'),('Kevein','jj'),('Newton','kk'),('Kart','ll');
insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15),('000013','Richard','Male',19),('000014','Mask','Male',55),('000015','Yong','Male',23),
('000016','Catt','Female',13),('000017','Tesla','Male',76),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);
insert into testreport values 
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 09:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 14:00:00'),
('000003','2021-10-04 19:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 08:00:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Betty','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Bob','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-10-04 08:32:00','Coughid-21','Cindy','2021-10-04 10:00:00','negative','2021-10-04 11:17:00'),
('000007','2021-10-04 16:59:00','Coughid-21','Curry','2021-10-04 19:00:00','negative','2021-10-04 21:19:00'),
('000008','2021-10-04 21:12:00','Coughid-21','Curry','2021-10-05 08:11:00','negative','2021-10-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Cindy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 10:12:00','Coughid-21','Curry','2021-10-04 12:00:00','negative','2021-10-04 14:53:00'),
('000011','2021-10-05 13:51:00','Coughid-21','Alen','2021-10-05 15:29:00','negative','2021-10-05 18:00:00'),
('000012','2021-10-05 15:21:00','Coughid-21','Alen','2021-10-05 17:09:00','negative','2021-10-05 19:00:00'),
('000013','2021-10-05 10:00:00','Coughid-21','Bob','2021-10-05 11:56:00','negative','2021-10-05 14:00:00'),
('000014','2021-10-05 11:09:00','Coughid-21','Cindy','2021-10-05 13:50:00','positive','2021-10-05 16:40:00'),
('000015','2021-10-05 10:00:00','Coughid-21','Betty','2021-10-05 12:00:00','negative','2021-10-05 14:00:00'),
('000016','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','negative','2021-10-05 15:46:00'),
('000017','2021-10-05 12:00:00','Coughid-21','Curry','2021-10-05 15:00:00','negative','2021-10-05 18:26:00'),
('000018','2021-10-05 11:00:00','Coughid-21','Curry','2021-10-05 13:00:00','negative','2021-10-05 15:46:00'),
('000019','2021-10-05 11:00:59','Coughid-21','Curry','2021-10-05 13:05:00','negative','2021-10-05 15:59:00'),
('000020','2021-10-05 11:01:47','Coughid-21','Curry','2021-10-05 13:19:00','negative','2021-10-05 16:46:00');

select (infect2021 /total2021) as infectrate from
(select count(mobile) as infect2021 from
(select distinct mobile from testreport WHERE
result = 'positive' and collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') as t2) as t3,
(select count(mobile) as total2021 from
(select distinct mobile from testreport where 
collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') as t1) as t4;

-- use case 7
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');

insert into testhospital values ('aa','a'),('bb','a'),('cc','a'),('dd','b');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','aa'),('Bob','bb'),('Billy','bb'),('Betty','bb'),('Cathy','cc'),('Cindy','cc'),('Curry','cc'),
('Dave','dd'),('Dick','dd'),('Duck','dd');

insert into testpeople values ('000001','James','Male',30),('000002','Jason','Male',23),('000003','Jack','Male',25),('000004','Durant','Male',32),
('000005','Morant','Male',21),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',29),
('000010','Coco','Female',19),('000011','Mill','Male',54),('000012','Tedy','Male',15),('000013','Richard','Male',19),('000014','Mask','Male',55),('000015','Yong','Male',23),
('000016','Catt','Female',13),('000017','Tesla','Male',76),('000018','Bond','Male',59),('000019','lurix','Female',19),('000020','Lang','Female',19);

insert into testreport values 
('000001','2021-10-04 08:00:00','Coughid-21','Alen','2021-10-04 10:22:00','negative','2021-10-04 13:11:00'),
('000002','2021-10-04 21:00:00','Coughid-21','Alen','2021-10-04 11:00:00','negative','2021-10-04 22:00:00'),
('000003','2021-10-04 21:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 22:50:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Amy','2021-10-04 17:21:00','negative','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Alice','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-10-04 22:32:00','Coughid-21','Alice','2021-10-04 10:00:00','negative','2021-10-05 11:17:00'),
('000007','2021-10-04 16:59:00','Coughid-21','Bob','2021-10-04 19:00:00','negative','2021-10-04 21:19:00'),
('000008','2021-10-04 21:12:00','Coughid-21','Bob','2021-10-05 08:11:00','negative','2021-10-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Billy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 21:12:00','Coughid-21','Billy','2021-10-04 12:00:00','negative','2021-10-04 22:53:00'),
('000011','2021-10-04 13:51:00','Coughid-21','Betty','2021-10-05 15:29:00','negative','2021-10-04 18:00:00'),
('000012','2021-10-04 21:21:00','Coughid-21','Betty','2021-10-05 17:09:00','negative','2021-10-04 22:00:00'),
('000013','2021-10-04 21:00:00','Coughid-21','Cathy','2021-10-05 11:56:00','negative','2021-10-04 23:00:00'),
('000014','2021-10-04 11:09:00','Coughid-21','Cathy','2021-10-05 13:50:00','positive','2021-10-04 16:40:00'),
('000015','2021-10-04 20:30:00','Coughid-21','Cindy','2021-10-05 12:00:00','negative','2021-10-04 21:50:00'),
('000016','2021-10-04 11:00:00','Coughid-21','Cindy','2021-10-05 13:00:00','negative','2021-10-04 15:46:00'),
('000017','2021-10-04 20:50:00','Coughid-21','Dick','2021-10-05 15:00:00','negative','2021-10-04 22:26:00'),
('000018','2021-10-04 11:00:00','Coughid-21','Dave','2021-10-05 13:00:00','negative','2021-10-04 15:46:00'),
('000019','2021-10-05 11:00:59','Coughid-21','Curry','2021-10-05 13:05:00','negative','2021-10-05 15:59:00'),
('000020','2021-10-05 11:01:47','Coughid-21','Alen','2021-10-05 13:19:00','negative','2021-10-05 16:46:00');

select hospitalname from
(select max(avgtime) as latest, hospitalname from
(select avg(offdutytime) as avgtime,hospitalname from
(select max(collecttime) as offdutytime, doctorname,hospitalname from testreport natural join testdoctor
WHERE
doctorname in 
(select doctorname from testdoctor WHERE
hospitalname IN
(select hospitalname from testhospital
where districtname = 'a')) and collecttime >= '2021-10-4 00:00:00' and collecttime < '2021-10-5 00:00:00'
group by doctorname) as t0
group by hospitalname) as t1) as t2;

-- use case 8
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');
insert into sampletype values 
('Coughid-21','* Coughid-21 is a newly identified type of virus this year, all patients tested to be positive should rest well and avoid going outside');

insert into testhospital values ('aa','a'),('bb','a'),('cc','b'),('dd','b'),('ee','c'),('ff','d'),('gg','e'),('hh','f'),('ii','g'),('jj','h'),('kk','i'),('ll','j');
insert into testdoctor values ('Alen','aa'),('Amy','aa'),('Alice','bb'),('Bob','bb'),('Billy','cc'),('Betty','cc'),('Cathy','dd'),('Cindy','ee'),('Curry','ff'),
('Dave','gg'),('Dick','hh'),('Duck','ii'),('Kevein','jj'),('Newton','kk'),('Kart','ll');

insert into testpeople values ('000001','James','Male',6),('000002','Jason','Male',8),('000003','Jack','Male',11),('000004','Durant','Male',12),
('000005','Morant','Male',4),('000006','Mina','Female',24),('000007','Lexie','Female',29),('000008','Cinderela','Female',18),('000009','Harden','Male',16),
('000010','Coco','Female',21),('000011','Mill','Male',54),('000012','Tedy','Male',60),('000013','Richard','Male',33),('000014','Mask','Male',41),('000015','Yong','Male',45),
('000016','Catt','Female',63),('000017','Tesla','Male',76),('000018','Bond','Male',81),('000019','lurix','Female',71),('000020','Lang','Female',69);

insert into testreport values 
('000001','2021-08-04 08:00:00','Coughid-21','Alen','2021-08-04 10:22:00','negative','2021-08-04 13:11:00'),
('000002','2021-02-04 21:00:00','Coughid-21','Alen','2021-02-04 11:00:00','negative','2021-02-04 22:00:00'),
('000003','2021-10-04 21:43:00','Coughid-21','Amy','2021-10-04 21:00:00','negative','2021-10-04 22:50:00'),
('000004','2021-10-04 15:55:00','Coughid-21','Amy','2021-10-04 17:21:00','positive','2021-10-04 19:33:00'),
('000005','2021-10-04 11:52:00','Coughid-21','Alice','2021-10-04 14:00:00','negative','2021-10-04 16:43:00'),
('000006','2021-11-05 22:32:00','Coughid-21','Alice','2021-11-05 10:00:00','negative','2021-11-05 11:17:00'),
('000007','2021-12-04 16:59:00','Coughid-21','Bob','2021-12-04 19:00:00','positive','2021-12-04 21:19:00'),
('000008','2021-06-04 21:12:00','Coughid-21','Bob','2021-06-05 08:11:00','negative','2021-06-05 10:00:00'),
('000009','2021-10-04 09:45:00','Coughid-21','Billy','2021-10-04 11:11:00','negative','2021-10-04 15:00:00'),
('000010','2021-10-04 21:12:00','Coughid-21','Billy','2021-10-04 12:00:00','positive','2021-10-04 22:53:00'),
('000011','2021-10-04 13:51:00','Coughid-21','Betty','2021-10-05 15:29:00','negative','2021-10-04 18:00:00'),
('000012','2021-10-04 21:21:00','Coughid-21','Betty','2021-10-05 17:09:00','negative','2021-10-04 22:00:00'),
('000013','2021-10-04 21:00:00','Coughid-21','Cathy','2021-10-05 11:56:00','negative','2021-10-04 23:00:00'),
('000014','2021-10-04 11:09:00','Coughid-21','Cathy','2021-10-05 13:50:00','positive','2021-10-04 16:40:00'),
('000015','2021-10-04 20:30:00','Coughid-21','Cindy','2021-10-05 12:00:00','negative','2021-10-04 21:50:00'),
('000016','2021-10-04 11:00:00','Coughid-21','Cindy','2021-10-05 13:00:00','positive','2021-10-04 15:46:00'),
('000017','2021-10-04 20:50:00','Coughid-21','Dick','2021-10-05 15:00:00','positive','2021-10-04 22:26:00'),
('000018','2021-10-04 11:00:00','Coughid-21','Dave','2021-10-05 13:00:00','negative','2021-10-04 15:46:00'),
('000019','2021-10-05 11:00:59','Coughid-21','Curry','2021-10-05 13:05:00','negative','2021-10-05 15:59:00'),
('000020','2021-10-05 11:01:47','Coughid-21','Alen','2021-10-05 13:19:00','positive','2021-10-05 16:46:00');

select * from 
(select count(mobile) as positivenumber12 from testpeople as tp natural join testreport as tr WHERE
(collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') and 
age <= 12 and result = 'positive') as t1,
(select count(mobile) as positivenumber30 from testpeople as tp natural join testreport as tr WHERE
(collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') and 
(age > 12 and age <= 30) and result = 'positive') as t2,
(select count(mobile) as positivenumber60 from testpeople as tp natural join testreport as tr WHERE
(collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') and 
(age > 30 and age <= 60) and result = 'positive') as t3,
(select count(mobile) as positivenumberover60 from testpeople as tp natural join testreport as tr WHERE
(collecttime >= '2021-01-01 00:00:00' and collecttime < '2022-01-01 00:00:00') and 
age > 60 and result = 'positive') as t4;

-- use case 9
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');

insert into bslocation values ('1,1','a'),('2,2','a'),('3,3','b'),('4,4','b'),('5,5','c'),('6,6','c'),('7,7','d'),('8,8','d'),('9,9','e'),('10,10','e'),('11,11','f'),('12,12','f'),
('13,13','g'),('14,14','g'),('15,15','h'),('16,16','h'),('17,17','i'),('18,18','i'),('19,19','j'),('20,20','j');

insert into bsrecord values ('10,10','000001','2021-10-01 00:00:00',null),('8,8','000002','2021-10-02 06:00:00','2021-10-04 12:00:00'),
('7,7','000002','2021-10-04 12:00:00',null),('14,14','000003','2021-09-26 08:00:00','2021-10-2 09:00:00'),('15,15','000003','2021-10-2 09:00:00',null),
('3,3','000004','2021-10-05 11:00:00',null),('1,1','000005','2021-10-01 09:00:00',null),('2,2','000006','2021-09-25 11:00:00',null),
('3,3','000007','2021-09-18 12:00:00','2021-10-03 21:00:00'),('2,2','000007','2021-10-03 21:00:00',null),('1,1','000008','2021-09-20 00:00:00','2021-10-02 20:00:00'),
('3,3','000008','2021-10-02 20:00:00','2021-10-03 22:00:00'),('1,1','000008','2021-10-03 22:00:00',null),('2,2','000009','2021-08-01 12:00:00',null),
('1,1','000010','2021-09-01 08:00:00',null);

select simid from bsrecord WHERE
disconnectiontime is null and gpslocation IN
(select gpslocation from bslocation WHERE
corresponding_district = 'a');

-- use case 10
insert into region values ('central','north','south','east','west');
insert into risklevel (districtname, regionname) values ('a','central'),('b','central'),('c','north'),('d','north'),('e','south'),('f','south'),
('g','east'),('h','east'),('i','west'),('j','west');

insert into testhospital values ('aa','a'),('bb','a'),('cc','b'),('dd','b'),('ee','c'),('ff','d'),('gg','e'),('hh','f'),('ii','g'),('jj','h'),('kk','i'),('ll','j');

select hospitalname from testhospital WHERE
districtname in 
(select districtname from risklevel WHERE
regionname = 'north');



