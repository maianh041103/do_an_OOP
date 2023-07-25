create database do_an;
use do_an;
drop database do_an;

drop table mon_hoc;


create table mon_hoc(
	ma_mon nvarchar(10) not null primary key,
    ten_mon nvarchar(50) not null,
    so_tin_chi int ,
    so_tiet_li_thuyet int,
    so_tiet_thuc_hanh int,
    trang_thai int
);

drop table lop_quan_li;

create table lop_quan_li(
	ma_lop_quan_li nvarchar(10) not null primary key,
    ten_khoa nvarchar(50) not null,
    gvcn nvarchar(50) not null,
	hoc_ki nvarchar(10) not null ,
    trang_thai int
);

drop table quan_li_sinh_vien;

create table quan_li_sinh_vien (
	ma_sinh_vien nvarchar(10) not null,
    ten_sinh_vien nvarchar(50) not null,
    ngay_sinh date,
    gioi_tinh boolean,
    dia_chi nvarchar(50),
    trang_thai int,
    primary key(ma_sinh_vien),
    ma_lop_quan_li nvarchar(10) not null ,
    foreign key(ma_lop_quan_li) references lop_quan_li(ma_lop_quan_li)
);

drop table quan_li_lop_hoc_phan;
create table quan_li_lop_hoc_phan(
	ma_mon nvarchar(50) not null,
	ma_lop_hoc_phan nvarchar(10) not null ,
	ten_lop_hoc_phan nvarchar(30) not null,
    giang_vien nvarchar(50) not null,
    trang_thai int,
    primary key(ma_lop_hoc_phan),
    foreign key(ma_mon) references mon_hoc(ma_mon)
);

drop table bao_cao_thong_ke;
create table bao_cao_thong_ke(
	ma_lop_hoc_phan nvarchar(10) not null,
    foreign key(ma_lop_hoc_phan) references quan_li_lop_hoc_phan(ma_lop_hoc_phan)
);

drop table lop_hoc_phan_has_sinh_vien;
create table lop_hoc_phan_has_sinh_vien (
	ma_sinh_vien nvarchar(10) not null,
	ma_lop_hoc_phan nvarchar(10) not null ,
    diem_qt double,
    diem_thi double,
    trang_thai int,
    foreign key(ma_sinh_vien) references quan_li_sinh_vien(ma_sinh_vien),
    foreign key(ma_lop_hoc_phan) references quan_li_lop_hoc_phan(ma_lop_hoc_phan)
);

drop table chuong_trinh_khung;
create table chuong_trinh_khung(
	ten_khoa nvarchar(10) not null,
    hoc_ki int,
    ma_mon nvarchar(50) not null,
    foreign key(ma_mon) references mon_hoc(ma_mon)
);

INSERT INTO chuong_trinh_khung
VALUES
	('CNTT',1,'250101'),('CNTT',1,'250103'),('CNTT',1,'390111'),('CNTT',1,'390121'),('CNTT',1,'440211'),('CNTT',1,'450101'),
    ('CNTT',1,'480111'),('CNTT',1,'480112'),('CNTT',1,'480113'),('CNTT',1,'480114'),('CNTT',1,'510201'),('CNTT',1,'531701'),('CNTT',1,'941701'),
    
    ('CNTT',2,'250102'),('CNTT',2,'396602'),('CNTT',2,'406601'),('CNTT',2,'420111'),('CNTT',2,'430101'),('CNTT',2,'430102'),
	('CNTT',2,'430103'),('CNTT',2,'440212'),('CNTT',2,'461727'),('CNTT',2,'471737'),('CNTT',2,'536602'),
    
    ('CNTT',3,'400101'),('CNTT',3,'420112'),('CNTT',3,'440213'),('CNTT',3,'461762'),('CNTT',3,'471754'),
    ('CNTT',3,'471756'),('CNTT',3,'471757'),('CNTT',3,'471787'),('CNTT',3,'531702'),
    
    ('CNTT',4,'401712'),('CNTT',4,'420113'),('CNTT',4,'440215'),('CNTT',4,'461706'),('CNTT',4,'461730'),
	('CNTT',4,'461731'),('CNTT',4,'471732'),('CNTT',4,'471755'),('CNTT',4,'531787'),
    
    ('CNTT',5,'418802'),('CNTT',5,'461751'),('CNTT',5,'471733'),('CNTT',5,'471734'),('CNTT',5,'471788'),
    ('CNTT',5,'471789'),('CNTT',5,'471790'),('CNTT',5,'471791'),('CNTT',5,'471792'),
    
    ('CNTT',6,'418801'),('CNTT',6,'461716'),('CNTT',6,'471793'),('CNTT',6,'471794'),('CNTT',6,'471795'),
    ('CNTT',6,'471796'),('CNTT',6,'471797'),('CNTT',6,'471798'),('CNTT',6,'531788'),
    
    ('CNTT',7,'476680'),('CNTT',7,'476681');


INSERT INTO lop_hoc_phan_has_sinh_vien (ma_sinh_vien, ma_lop_hoc_phan, diem_qt, diem_thi) 
VALUES ('0178798','1234',5,8.7);

drop table dang_nhap;
create table dang_nhap(
	tai_khoan nvarchar(50) not null,
    mat_khau nvarchar(50) not null,
    sdt nvarchar(50) not null
);


insert into dang_nhap
value ("nguyenmaianh","maianh20","0123456789");
