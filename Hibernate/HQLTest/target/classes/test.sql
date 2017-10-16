    alter table Commodity         drop         foreign key FK1EDE370D8206AB12;
    alter table OrderForm         drop         foreign key FK6014D112F13C0221;
    alter table OrderItem         drop         foreign key FK60163F617D74C706;
    alter table OrderItem         drop         foreign key FK60163F61BEDDE17F;
    drop table if exists Commodity  ;  
    drop table if exists Customer;
    drop table if exists OrderForm;
    drop table if exists OrderItem;
    drop table if exists Seller;
    create table Commodity (
        id integer not null auto_increment,
        category varchar(255),
        description varchar(255),
        name varchar(255),
        price double precision not null,
        unit varchar(255),
        sid integer,
        primary key (id)
    );

    create table Customer (
        id integer not null auto_increment,
        address varchar(255),
        age integer not null,
        birthday datetime,
        description varchar(255),
        email varchar(255),
        name varchar(255),
        sex varchar(255),
        tel varchar(255),
        primary key (id)
    );

    create table OrderForm (
        id integer not null auto_increment,
        amount double precision not null,
        status varchar(255),
        tradedate datetime,
        cid integer,
        primary key (id)
    );

    create table OrderItem (
        id integer not null auto_increment,
        actprice double precision not null,
        amount double precision not null,
        discount double precision not null,
        cid integer,
        ofid integer,
        primary key (id)
    );

    create table Seller (
        id integer not null auto_increment,
        address varchar(255),
        business varchar(255),
        name varchar(255),
        star varchar(255),
        tel varchar(255),
        wesite varchar(255),
        primary key (id)
    );

    alter table Commodity 
        add index FK1EDE370D8206AB12 (sid), 
        add constraint FK1EDE370D8206AB12 
        foreign key (sid) 
        references Seller (id);

    alter table OrderForm 
        add index FK6014D112F13C0221 (cid), 
        add constraint FK6014D112F13C0221 
        foreign key (cid) 
        references Customer (id);

    alter table OrderItem 
        add index FK60163F617D74C706 (cid), 
        add constraint FK60163F617D74C706 
        foreign key (cid) 
        references Commodity (id);

    alter table OrderItem 
        add index FK60163F61BEDDE17F (ofid), 
        add constraint FK60163F61BEDDE17F 
        foreign key (ofid) 
        references OrderForm (id);


insert into seller (name,tel,address,wesite,star,business) values ("A服装店","130000000000","中国北京XX区","www.a.com","5","经营各式服装");
insert into seller (name,tel,address,wesite,star,business) values ("B数码店","158000000000","中国浙江杭州市XX区","www.b.com","4","经营各类电子产品");
insert into seller (name,tel,address,wesite,star,business) values ("C电器店","130121345678","中国广东深圳市XX区","www.c.com","4","经营各类家电");
insert into seller (name,tel,address,wesite,star,business) values ("D书店","131852963745","中国河南郑州市XX区","www.d.com","5","经营电子书和实体书");

insert into Customer (name,tel,address,email,sex,description,age,birthday) 
	values ("张三","13845678941","中国上海XXX区XX路","zhangsan@zhangsan.com","男","热爱程序的编程员",20,"1990-01-01");
insert into Customer (name,tel,address,email,sex,description,age,birthday) 
	values ("李四","13000000000","中国北京XXX区XX路","lisi@163.com","女","快购物白领",25,"1990-02-21");
insert into Customer (name,tel,address,email,sex,description,age,birthday) 
	values ("王五","12222222222","中国广东深圳XXX区XX路","wangwu@163.com","男","宅男",35,"1980-01-14");
	
insert into Commodity (name,price,unit,category,description,sid) 
	values ("中式童装",120,"套","童装","中式实际儿童服装",1);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("女士套装",200,"套","女装","女士职业服装",1);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("男士西服",200,"套","男装","男士西服套装",1);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("笔记本电脑",4000,"台","电脑","双核笔记本电脑",2);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("移动硬盘",400,"块","电脑配件","1T移动硬盘",2);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("液晶电视",5000,"台","电视","4K高清液晶电视",3);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("滚筒洗衣机",4000,"台","洗衣机","滚筒洗衣机",3);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("《hibernate编程》",30,"实体书","童装","hibernate编程",4);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("《Java核心》",50,"本","实体书","java核心变成",4);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("《海底两万里》",40,"本","电子书","经典科幻小说",4);
insert into Commodity (name,price,unit,category,description,sid) 
	values ("《白夜行》",400,"本","电子书","null",4);

 select *
      --  count(*) as y0_,
       -- avg(this_.price) as y1_,
      --  this_.sid as y2_ 
    from       Commodity this_ 
    where
        this_.description is not null and (this_.price>=1000 or this_.price=400 ) 
    --group by        this_.sid     order by        this_.price desc limit 10
	
        
	
	
insert into OrderForm (cid,tradedate,status,amount) values (1,"2015-04-30","已收货",4400);
insert into OrderForm (cid,tradedate,status,amount) values (2,"2015-05-11","已发货",520);
insert into OrderForm (cid,tradedate,status,amount) values (3,"2015-05-13","已收款",9120);


insert into orderItem (ofid,cid,discount,actprice,amount) values (1,4,1,4000,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (1,5,1,400,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (2,1,1,120,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (2,2,1,200,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (2,3,1,200,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (3,6,1,5000,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (3,7,1,4000,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (3,8,1,30,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (3,9,1,50,1);
insert into orderItem (ofid,cid,discount,actprice,amount) values (3,10,1,40,1);
	
	