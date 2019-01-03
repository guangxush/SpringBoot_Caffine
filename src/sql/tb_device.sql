CREATE TABLE `tb_devices` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`device_name`  varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`price`  decimal(10,0) NULL DEFAULT NULL ,
`count`  int(10) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=2
ROW_FORMAT=DYNAMIC
;