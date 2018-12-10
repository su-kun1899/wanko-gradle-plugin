# wanko-gradle-plugin

[![CircleCI](https://circleci.com/gh/su-kun1899/wanko-gradle-plugin/tree/master.svg?style=svg)](https://circleci.com/gh/su-kun1899/wanko-gradle-plugin/tree/master)

## Usage

```groovy
buildscript {
	repositories {
		maven { url 'https://plugins.gradle.org/m2/' }
	}

	// You have to add Driver's classpath
	dependencies {
		classpath 'org.postgresql:postgresql:9.3-1100-jdbc41'
	}
}

plugins {
	// apply plugin
	id 'red.sukun1899.wanko' version '1.0.0'
}

wanko {
	// database configuration
	url = 'jdbc:postgresql://localhost:5432/mydb'
	user = 'mydb_user'
	password = 'mydb_pass'
	driverClassName = 'org.postgresql.Driver'
	
	// sql file location
	sqlDir = '/path/to/sql/location'
}
```
