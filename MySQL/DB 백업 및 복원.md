## 모든 DB 백업 및 복원

### 모든 DB 백업

```shell
$ mysqldump --all-databases -u[사용자 계정] -p --default-character-set=euckr > [백업된 DB].sql
```

### 모든 DB 복원

```shell
$ mysql --all-databases -u[사용자 계정] -p < [백업된 DB].sql
```





## DB 백업 및 복원

### DB 백업

```shell
$ mysqldump -u[사용자 계정] -p[패스워드] [원본 데이터베이스명] > [생성할 백업 DB명].sql
```

### DB 복원

```shell
$ mysql -u[사용자 계정] -p[패스워드] [복원할 DB] < [백업된 DB].sql
```



## 테이블 백업 및 복원

### 테이블 백업

```shell
$ mysqldump -u[사용자 계정] -p[패스워드] [데이터베이스명] [원본 백업받을 테이블명] > [백업받을 테이블명].sql
```

### 테이블 복원

```shell
$ mysql -u[사용자 계정] -p[패스워드] [복원할 DB ] < [백업된 테이블].sql
```



