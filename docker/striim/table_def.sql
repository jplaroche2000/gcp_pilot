*+- Defgen version 5.0, Encoding windows-1252
*
* Definitions created/modified  2019-10-03 11:45
*
*  Field descriptions for each column entry:
*
*     1    Name
*     2    Data Type
*     3    External Length
*     4    Fetch Offset
*     5    Scale
*     6    Level
*     7    Null
*     8    Bump if Odd
*     9    Internal Length
*    10    Binary Length
*    11    Table Length
*    12    Most Significant DT
*    13    Least Significant DT
*    14    High Precision
*    15    Low Precision
*    16    Elementary Item
*    17    Occurs
*    18    Key Column
*    19    Sub Data Type
*    20    Native Data Type
*    21    Character Set
*    22    Character Length
*    23    LOB Type
*    24    Partial Type
*
Database type: ORACLE
Character set ID: we8mswin1252
National character set ID: UTF-16
Locale: neutral
Case sensitivity: 14 14 14 14 14 14 14 14 14 14 14 14 11 14 14 14
TimeZone: GMT
*
Definition for table SCOTT.ACCOUNT
Record length: 220
Syskey: 0
Columns: 5
ID           64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
BAN          64     50       56  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
ACCOUNT_ID   64     50      112  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
NAME         64     20      168  0  0 1 0     20     20      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
LAST_NAME    64     20      194  0  0 1 0     20     20      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.ADDRESS
Record length: 244
Syskey: 0
Columns: 4
ID              64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
STREET_NUMBER   64     50       56  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
STREET          64     60      112  0  0 1 0     60     60      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
CITY            64     60      178  0  0 1 0     60     60      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.ADDRESS_LINK
Record length: 168
Syskey: 0
Columns: 3
ID            64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
ADDRESS_ID    64     50       56  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
CUSTOMER_ID   64     50      112  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
End of definition
*
Definition for table SCOTT.BILLING_ACCOUNT
Record length: 430
Syskey: 0
Columns: 18
BAN                    64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
ACCOUNT_SUB_TYPE       64      1       56  0  0 1 0      1      1      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
ACCOUNT_TYPE           64      1       62  0  0 1 0      1      1      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
AR_BALANCE             64     50       68  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
BILL_CYCLE             64     50      124  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
BILLING_LANGUAGE       64      2      180  0  0 1 0      2      2      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
BL_EBPP_IND            64      1      188  0  0 1 0      1      1      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
CLA                    64     50      194  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
COL_DELINQ_STATUS      64      1      250  0  0 1 0      1      1      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
COMPANY_CODE           64      9      256  0  0 1 0      9      9      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
COMPANY_NAME           64     40      270  0  0 1 0     40     40      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
STATUS_ACTV_CODE       64      3      316  0  0 1 0      3      3      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
STATUS_ACTV_RSN_CODE   64      4      324  0  0 1 0      4      4      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
SYS_CREATION_DATE     192     19      334  0  0 1 0     19     19     19 0 5 0 0 1    0 0   0   12       -1      0 0 0
SYS_UPDATE_DATE       192     19      356  0  0 1 0     19     19     19 0 5 0 0 1    0 0   0   12       -1      0 0 0
AUTO_GEN_PYM_TYPE      64     15      378  0  0 1 0     15     15      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
BOGUS_1                64     10      398  0  0 1 0     10     10      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
BOGUS_2                64     10      414  0  0 1 0     10     10      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.BONUS
Record length: 142
Syskey: 0
Columns: 4
ENAME   64     10        0  0  0 1 0     10     10      0 0 0 0 0 1    0 1   0    1       -1      0 0 0
JOB     64      9       16  0  0 1 0      9      9      0 0 0 0 0 1    0 1   0    1       -1      0 0 0
SAL     64     50       30  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
COMM    64     50       86  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
End of definition
*
Definition for table SCOTT.CUSTOMER
Record length: 128
Syskey: 0
Columns: 3
ID          64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
NAME        64     30       56  0  0 1 0     30     30      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
LAST_NAME   64     30       92  0  0 1 0     30     30      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.CUSTOMER_ORDER
Record length: 200
Syskey: 0
Columns: 4
ID            64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
ORDER_DATE   192     29       56  0  0 1 0     29     29     29 0 6 0 0 1    0 0   0  187       -1      0 0 0
CUSTOMER_ID   64     50       88  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
TOTAL         64     50      144  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
End of definition
*
Definition for table SCOTT.CUSTOMER_ORDER_ITEM
Record length: 260
Syskey: 0
Columns: 5
ID                  64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
CUSTOMER_ORDER_ID   64     50       56  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
DESCRIPTION         64     30      112  0  0 1 0     30     30      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
QUANTITY            64     50      148  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
PRICE               64     50      204  0  0 1 0     50     50     50 0 0 0 0 1    0 0   2    2       -1      0 0 0
End of definition
*
Definition for table SCOTT.DEPT
Record length: 50
Syskey: 0
Columns: 3
DEPTNO  134      8        0  0  0 1 0      8      8      8 0 0 0 0 1    0 1   3    2       -1      0 0 0
DNAME    64     14       12  0  0 1 0     14     14      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
LOC      64     13       32  0  0 1 0     13     13      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.EMP
Record length: 112
Syskey: 0
Columns: 8
EMPNO     134      8        0  0  0 1 0      8      8      8 0 0 0 0 1    0 1   3    2       -1      0 0 0
ENAME      64     10       12  0  0 1 0     10     10      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
JOB        64      9       28  0  0 1 0      9      9      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
MGR       134      8       42  0  0 1 0      8      8      8 0 0 0 0 1    0 0   3    2       -1      0 0 0
HIREDATE  192     19       54  0  0 1 0     19     19     19 0 5 0 0 1    0 0   0   12       -1      0 0 0
SAL       134      9       76  2  0 1 0      8      8      8 0 0 0 0 1    0 0   3    2       -1      0 0 0
COMM      134      9       88  2  0 1 0      8      8      8 0 0 0 0 1    0 0   3    2       -1      0 0 0
DEPTNO    134      8      100  0  0 1 0      8      8      8 0 0 0 0 1    0 0   3    2       -1      0 0 0
End of definition
*
Definition for table SCOTT.QUIESCEMARKER
Record length: 426
Syskey: 0
Columns: 7
SOURCE         64    100        0  0  0 1 0    100    100      0 0 0 0 0 1    0 1   0    1       -1      0 0 0
STATUS         64    100      106  0  0 1 0    100    100      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
SEQUENCE      134     11      212  0  0 1 0      8      8      8 0 0 0 0 1    0 1   3    2       -1      0 0 0
INITTIME      192     29      224  0  0 1 0     29     29     29 0 6 0 0 1    0 0   0  187       -1      0 0 0
UPDATETIME    192     29      256  0  0 1 0     29     29     29 0 6 0 0 1    0 0   0  187       -1      0 0 0
APPROVEDTIME  192     29      288  0  0 1 0     29     29     29 0 6 0 0 1    0 0   0  187       -1      0 0 0
REASON         64    100      320  0  0 1 0    100    100      0 0 0 0 0 1    0 0   0    1       -1      0 0 0
End of definition
*
Definition for table SCOTT.SALGRADE
Record length: 168
Syskey: 0
Columns: 3
GRADE   64     50        0  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
LOSAL   64     50       56  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
HISAL   64     50      112  0  0 1 0     50     50     50 0 0 0 0 1    0 1   2    2       -1      0 0 0
End of definition
