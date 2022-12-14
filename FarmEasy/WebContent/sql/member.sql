create table TB_MEMBER(
    m_seq int not null primary key,
    m_name varchar2(15) not null,
    m_Id varchar2(20) not null,
    m_pw varchar2(30) not null,
    m_email VARCHAR2(50) not null,
    m_mobile VARCHAR2(20) not null,
    m_authority VARCHAR2(3) DEFAULT 'N',
    m_date DATE DEFAULT SYSDATE
);

create SEQUENCE m_seq start with 1 INCREMENT by 1;


drop table TB_MEMBER;

drop sequence m_seq;