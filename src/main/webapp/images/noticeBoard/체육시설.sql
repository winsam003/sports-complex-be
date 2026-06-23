CREATE TABLE `Member` (
	`ID`	varchar(15)	NOT NULL	COMMENT 'PK',
	`usercode`	varchar(6)	NOT NULL	COMMENT '아동, 성인, 노인',
	`name`	varchar(10)	NOT NULL,
	`password`	varchar(100)	NOT NULL,
	`birth`	varchar(8)	NOT NULL,
	`phoneNum`	varchar(11)	NOT NULL,
	`homeNum`	varchar(11)	NULL,
	`address`	varchar(100)	NOT NULL	COMMENT '주소찾기 API에 전송되는 우편번호',
	`address1`	varchar(100)	NOT NULL	COMMENT '주소찾기 API 결과값으로 나오는 도로명 주소',
	`address2`	varchar(30)	NULL	COMMENT '상세주소',
	`email`	varchar(50)	NOT NULL,
	`snsAgr`	boolean	NOT NULL	DEFAULT 1	COMMENT '1 - YES / 0- NO',
	`emailAgr`	boolean	NOT NULL	DEFAULT 1	COMMENT '1 - YES / 0- NO',
	`carNum`	varchar(10)	NULL,
	`parkUse`	date	NULL
);

CREATE TABLE `Event` (
	`eventCode`	int	NOT NULL,
	`eventName`	varchar(20)	NOT NULL,
	`eventDetail`	varchar(1000)	NOT NULL,
	`eventFacility`	varchar(20)	NOT NULL,
	`eventTime`	varchar(20)	NOT NULL,
	`eventFor`	varchar(5)	NOT NULL,
	`eventType`	varchar(10)	NOT NULL	COMMENT '스포츠 행사, 일반 행사, 대회',
	`eventCount`	int	NOT NULL	COMMENT '조회수',
	`eventUploadfile`	varchar(100)	NULL,
	`stfID`	varchar(15)	NOT NULL,
	`eventDate`	DATE	NOT NULL
);

CREATE TABLE `Classes` (
	`clNum`	int	NOT NULL,
	`clDays`	varchar(25)	NOT NULL,
	`clTime`	varchar(10)	NOT NULL,
	`classCode`	varchar(10)	NOT NULL,
	`clName`	varchar(10)	NOT NULL,
	`clRequest`	timestamp	NOT NULL,
	`clRequestEnd`	timestamp	NOT NULL,
	`clStart`	timestamp	NOT NULL,
	`clEnd`	timestamp	NOT NULL,
	`clFor`	varchar(10)	NOT NULL	COMMENT 'KI아동, HT청소년, AD성인, OL노인',
	`clCount`	int	NOT NULL,
	`clWating`	int	NOT NULL,
	`clPrice`	int	NOT NULL,
	`clType`	varchar(10)	NULL	COMMENT '접수가능, 예약대기, 대기마감, 접수 마감',
	`Key`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `Notice` (
	`notNum`	int	NOT NULL,
	`notTitle`	varchar(20)	NOT NULL,
	`quesT`	varchar(3)	NOT NULL	DEFAULT false	COMMENT '시설 / 강좌 / 일반',
	`notDate`	DATE	NOT NULL,
	`notUploadfile`	varchar(100)	NULL,
	`notCount`	int	NOT NULL,
	`notDetail`	varchar(1000)	NOT NULL,
	`notType`	boolean	NOT NULL	COMMENT 'F:공지사항, Ture:자주하는질문',
	`stfID`	varchar(15)	NOT NULL
);

CREATE TABLE `classApp` (
	`srnum`	int	NOT NULL,
	`ID`	varchar(15)	NOT NULL,
	`srdate`	date	NOT NULL,
	`srstate`	varchar(5)	NOT NULL,
	`payment`	varchar(10)	NULL,
	`clNum`	VARCHAR(255)	NOT NULL,
	`Key`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `spaceRentApp` (
	`spRNum`	int	NOT NULL,
	`Key`	varchar(10)	NOT NULL,
	`spRDate`	date	NOT NULL,
	`ID`	varchar(15)	NOT NULL	COMMENT 'PK',
	`spRState`	varchar(5)	NOT NULL	COMMENT '접수 / 결제 대기/ 확정',
	`payment`	varchar(10)	NULL,
	`gameApp`	boolean	NOT NULL	DEFAULT false,
	`ID2`	varchar(15)	NULL	COMMENT 'PK',
	`spRState2`	varchar(5)	NULL,
	`payment2`	varchar(15)	NULL
);

CREATE TABLE `parkApp` (
	`parkAppNum`	int	NOT NULL	COMMENT 'PK',
	`ID`	varchar(15)	NOT NULL	COMMENT '회원 테이블에서 가져온PK',
	`parkAppDate`	date	NOT NULL,
	`parkUseDate`	DATE	NOT NULL,
	`parkAppCancel`	varchar(5)	NOT NULL,
	`payment`	varchar(10)	NULL,
	`parkPrice`	int	NULL,
	`Key`	varchar(10)	NOT NULL
);

CREATE TABLE `teach` (
	`teachNum`	int	NOT NULL	COMMENT 'Auto',
	`teachCode`	varchar(10)	NOT NULL,
	`teachName`	varchar(10)	NOT NULL,
	`teachBirth`	date	NOT NULL,
	`teachPhone`	varchar(15)	NULL,
	`teachAdress`	varchar(100)	NULL,
	`teachLicense`	varchar(50)	NULL,
	`teachAccount`	varchar(30)	NOT NULL,
	`teachRDate`	date	NOT NULL
);

CREATE TABLE `Qna` (
	`qaNum`	long	NOT NULL	COMMENT '식별 가능한 코드',
	`ID`	varchar(15)	NOT NULL	COMMENT 'PK - 질문하는 회원 아이디 (회원테이블)',
	`qaTitle`	varchar(20)	NOT NULL,
	`qaContent`	varchar(1000)	NOT NULL	COMMENT '게시글의 내용',
	`qaType`	varchar(10)	NOT NULL	COMMENT '시설문의 , 일반문의, 수강문의',
	`qaDate`	date	NOT NULL,
	`qaOpen`	boolean	NOT NULL	DEFAULT 0	COMMENT '1 - 공개 / 0- 비공개',
	`qaPassword`	int	NULL	COMMENT '게시글의 비밀번호',
	`qaCount`	int	NOT NULL	DEFAULT 0,
	`qaFile`	varchar(100)	NULL	COMMENT '첨부파일 명',
	`qaReply`	varchar(1000)	NULL	COMMENT '답변이 존재하면 답변완료',
	`qaReplyTime`	date	NULL,
	`stfID`	varchar(15)	NULL
);

CREATE TABLE `staff` (
	`stfID`	varchar(15)	NOT NULL,
	`stfPassword`	varchar(100)	NOT NULL,
	`stfDmp`	varchar(20)	NOT NULL,
	`stfLevel`	varchar(10)	NOT NULL,
	`stfName`	varchar(10)	NOT NULL,
	`stfPNum`	int	NOT NULL,
	`stfCode`	varchar(20)	NOT NULL
);

CREATE TABLE `space` (
	`Key`	varchar(10)	NOT NULL,
	`spaceName`	varchar(20)	NOT NULL,
	`spacePrice`	int	NOT NULL,
	`parkSpace`	int	NOT NULL	DEFAULT 1,
	`parking`	int	NOT NULL	DEFAULT 0
);

CREATE TABLE `classWaitingList` (
	`resKey`	int	NOT NULL
);

CREATE TABLE `banner` (
	`bannerNum`	int	NOT NULL,
	`eventNum`	int	NOT NULL	COMMENT '이벤트 테이블에서',
	`stfID`	varchar(15)	NOT NULL,
	`bannerImage`	varchar(100)	NULL	COMMENT '이미지가 없으면 NoImage 넣기.'
);

CREATE TABLE `TableCode` (
	`bigDiv`	varchar(5)	NULL,
	`midDiv`	varchar(5)	NULL,
	`smDiv`	varchar(5)	NULL,
	`divName`	varchar(10)	NULL,
	`divMore`	varchar(10)	NULL
);

ALTER TABLE `Member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`ID`
);

ALTER TABLE `Event` ADD CONSTRAINT `PK_EVENT` PRIMARY KEY (
	`eventCode`
);

ALTER TABLE `Classes` ADD CONSTRAINT `PK_CLASSES` PRIMARY KEY (
	`clNum`
);

ALTER TABLE `Notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`notNum`
);

ALTER TABLE `classApp` ADD CONSTRAINT `PK_CLASSAPP` PRIMARY KEY (
	`srnum`
);

ALTER TABLE `spaceRentApp` ADD CONSTRAINT `PK_SPACERENTAPP` PRIMARY KEY (
	`spRNum`
);

ALTER TABLE `parkApp` ADD CONSTRAINT `PK_PARKAPP` PRIMARY KEY (
	`parkAppNum`,
	`ID`
);

ALTER TABLE `teach` ADD CONSTRAINT `PK_TEACH` PRIMARY KEY (
	`teachNum`
);

ALTER TABLE `Qna` ADD CONSTRAINT `PK_QNA` PRIMARY KEY (
	`qaNum`
);

ALTER TABLE `staff` ADD CONSTRAINT `PK_STAFF` PRIMARY KEY (
	`stfID`
);

ALTER TABLE `space` ADD CONSTRAINT `PK_SPACE` PRIMARY KEY (
	`Key`
);

ALTER TABLE `classWaitingList` ADD CONSTRAINT `PK_CLASSWAITINGLIST` PRIMARY KEY (
	`resKey`
);

ALTER TABLE `banner` ADD CONSTRAINT `PK_BANNER` PRIMARY KEY (
	`bannerNum`,
	`eventNum`,
	`stfID`
);

ALTER TABLE `parkApp` ADD CONSTRAINT `FK_Member_TO_parkApp_1` FOREIGN KEY (
	`ID`
)
REFERENCES `Member` (
	`ID`
);

ALTER TABLE `banner` ADD CONSTRAINT `FK_Event_TO_banner_1` FOREIGN KEY (
	`eventNum`
)
REFERENCES `Event` (
	`eventCode`
);

ALTER TABLE `banner` ADD CONSTRAINT `FK_Event_TO_banner_2` FOREIGN KEY (
	`stfID`
)
REFERENCES `Event` (
	`stfID`
);

