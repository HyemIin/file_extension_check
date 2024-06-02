# 파일 확장자 차단 시스템 구현 과제
> ### FLOW 고객사 사용 환경에 따라 "특정 파일 확장자 차단" 을 수행할 수 있는 웹기반 시스템 개발

## ⭐️ 서비스 링크
> [FLOW_파일 확장자 차단 시스템](http://15.164.146.29:8080/)
<br>

## 💡 프로젝트 주제

- **"특정 파일 확장자 차단" 을 수행할 수 있는 웹기반 시스템**

<br>

## 🏗 아키텍처
> ![image](https://github.com/HyemIin/file_extension_check/assets/114489245/bc20f5bc-a91f-44a4-a434-58196e3c6b1a)


<br>

## 📝 프로젝트 개요

- [목적] FLOW 서비스의 서버 장애를 일으킬 수 있는 확장자를 사전에 선별적으로 차단할 수 있는 필터링 시스템을 구현
- [배경] 특정 파일 확장자의 경우, 서버에 업로드 시 의도치 않게 실행되어 서버 장애를 야기할 수 있음
> - exe,sh 등의 실행 파일 확장자의 경우 위와 같은 서버 장애를 발생시킬 수 있는 대표적인 확장자
> - 검증되지 않은 실행 파일 내부에 악성 코드가 심어져 있을 수 있기 떄문

<br>

## 📝 프로젝트 목표
- 사용자 선택에 따른 각 확장자 차단 기능 구현
- 각 확장자 CRUD 기능 구현
- 확장자 조정 시 사용자 편의성 확보

<br>

## 📝 프로젝트 문서 산출물
- 프로젝트 간트차트(FLOW 활용)
  ![image](https://github.com/HyemIin/file_extension_check/assets/114489245/32e58494-3fce-4987-881a-bec3a8687b1b)
- [프로젝트 정의서](https://hyem5019.notion.site/5669916c39d942b0912ceaa4d94ca15b?pvs=4)
- [요구사항 명세서](https://hyem5019.notion.site/75ee1a4d9a31467ba19756129ef3818a?pvs=4)
- [API 명세서](https://hyem5019.notion.site/API-8704db26f5ab4f31a14cad745ad0b702?pvs=4)

<br>

## 🎯 시스템 타겟 사용자
- 1. FLOW 서비스를 사용하는 고객사의 FLOW 운영 관리자
  > 고객사 판단 하에 차단이 필요한 확장자를 선택
  > 확장자 차단 시 해당 고객사에서 사용하는 FLOW 프로젝트에만 적용
- 2. FLOW 서비스 서버를 관리하는 FLOW사 자체 서버 관리자
  > FLOW 서버에 치명적일 수 있는 파일 타입을 일괄 관리
  > 확장자 차단 시 모든 FLOW 사용 고객사가 해당 확장자를 FLOW에 업로드할 수 없음
<br>

## ⭐️ 과제 기본 요구사항

### 고정 확장자

> 고정 확장자는 차단을 자주하는 확장자를 리스트이며, default는 unCheck되어져 있습니다. <br>
> 고정 확장자를 check or uncheck를 할 경우 db(FixExtension 테이블)에 체크여부가 저장됩니다. <br>
> 새로고침 시 고정 확장자 체크 여부가 유지됩니다.<br>

### 커스텀 확장자

> 확장자 최대 입력 길이는 20자리로 제한됩니다.<br>
> 추가 버튼 클릭 시 db(CustomExtension table)에 저장되며, 아래쪽 영역에 표현됩니다.<br>
> 커스텀 확장자는 최대 200개까지 추가가 가능합니다.<br>
> 확장자 옆 X(휴지통)을 클릭시 db에서 삭제 가능합니다. <br>
> 문의하기를 클릭해 서비스 공식 카카오톡 계정에 문의할 수 있다. <br>

## ❗️요구사항 이외 고려한 사항❗️

### 고정 확장자
- 고정 확장자 생성/삭제 로직 구현
  > 사용자 편의성을 고려하여 고정 확장자 Create,Delete가 가능합니다.
- 입력 길이 제한(20자) 구현
  > 고정 확장자 최대 입력 길이는 20자리로 제한됩니다.
- 중복 등록 차단 로직 구현
  > 고정 확장자 중복 등록이 차단됩니다.(확장자가 고정 or 커스텀 확장자로 이미 등록되어 있는 경우, 중복 등록할 수 없습니다.)
- 글자 타입 제한(영문 대소문자) 구현
  > 고정 확장자는 대소문자만으로 구성되어야합니다.(한글, 특수문자, 빈값 입력 방지)
- 삭제 시 사용자 확인 메세지 전송 로직 구현
  > 고정 확장자 삭제 시 삭제할 확장자 이름을 입력란에 입력해야하며, 컨펌 메세지로 삭제 최종 확인을 컨펌받아야 삭제됩니다.
- 미등록 확장자 삭제 불가 로직 구현
  > 미등록된 확장자를 삭제 요청할 경우, 알럿 메세지로 미등록 확장자라 삭제할 수 없다는 메세지를 출력합니다.
- 체크박스 해제 후 삭제 가능 로직 구현
  > 고정 확장자가 차단된 상태인 경우, 차단해제(체크박스 해제) 후 삭제 가능합니다.
- 입력 후 ENTER키 클릭 시 등록 로직 구현
  > 등록할 고정 확장자 명 입력 후 엔터키 클릭 시 고정 확장자로 등록됩니다.
- 고정 확장자 최대 입력 가능 개수 20개 제한 로직 구현
  > 고정 확장자는 최대 20개까지 등록할 수 있습니다.

### 커스텀 확장자
- 커스텀 확장자 일괄 삭제 기능 구현
  > 커스텀 확장자 초기화(일괄 삭제) 기능합니다.
- 커스텀 확장자 일괄 삭제 시 사용자 확인 메세지 전송 로직 구현
  > 커스텀 확장자 초기화(일괄 삭제) 클릭 시 컨펌 메세지로 삭제 최종 확인을 컨펌받아야 삭제됩니다.
- 중복 등록 차단 로직 구현
  > 커스텀 확장자 중복 등록이 차단됩니다.(확장자가 고정 or 커스텀 확장자로 이미 등록되어 있는 경우, 중복 등록할 수 없습니다.)
- 글자 타입 제한(영문 대소문자) 구현
  > 고정 확장자는 대소문자만으로 구성되어야합니다.(한글, 특수문자, 빈값 입력 방지)
- 새로고침 시 등록된 커스텀 확장자 유지 로직 구현
  > 커스텀 확장자로 등록한 경우, 새로고침 시에도 확장자 등록이 유지됩니다.
- 입력 후 ENTER키 클릭 시 등록 로직 구현
  > 등록할 커스텀 확장자 명 입력 후 엔터키 클릭 시 고정 확장자로 등록됩니다.
<br>

## ⚙️ 프로젝트 세팅

> 1. 자바 버전 : 17
> 2. 스프링부트 버전 : 3.2.6
> 3. 빌드 & 빌드 도구 : Gradle
> 4. Git 브랜치 전략 : Feature Branch → Develop Branch → Main Branch(배포)

<br>

## 🛠️ 기술 스택
#### Project Management Tool
![flow](https://img.shields.io/badge/FLOW-AF38F9.svg?style=for-the-badge)

#### Framework
![springboot](https://img.shields.io/badge/springboot-6DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)

#### DB
![postgresql](https://img.shields.io/badge/postgresql-4169E1.svg?style=for-the-badge&logo=postgresql&logoColor=white)

#### Library
![JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Lombok](https://img.shields.io/badge/lombok-E50005.svg?style=for-the-badge&logo=lospec&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/thymeleaf-005F0F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Validation](https://img.shields.io/badge/validation-7239B3.svg?style=for-the-badge&logo=vala&logoColor=white)

#### Document
![notion](https://img.shields.io/badge/notion-000000.svg?style=for-the-badge&logo=notion&logoColor=white)

#### Server
![ec2](https://img.shields.io/badge/AWS_EC2-FF9900.svg?style=for-the-badge&logo=amazonec2&logoColor=white)
![rds](https://img.shields.io/badge/AWS_rds-527FFF.svg?style=for-the-badge&logo=amazonrds&logoColor=white)

<br>

## 📐 ERD 설계도
![image](https://github.com/HyemIin/file_extension_check/assets/114489245/88268ef8-6f66-425d-836e-42ee78452631)

