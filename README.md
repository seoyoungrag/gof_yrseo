Conway's Game of Life_YRSEO
=========

`Conway's Game of Life_YRSEO`은 [라이프게임](https://ko.wikipedia.org/wiki/%EB%9D%BC%EC%9D%B4%ED%94%84_%EA%B2%8C%EC%9E%84)의 내용을 토대로 JAVA CONSOLE OUTPUT으로 구현했습니다. random board state 실행, 아규먼트를 통해 파일을 읽어 board state을 preset형태로 실행을 지원합니다. 

## Usage

1. random board state 실행.
`$ java -jar gof.jar`
2. preset 실행.
`$ java -jar gof.jar a.txt`
3. preset과 generation 실행 후 result.txt 파일에 마지막 generation을 저장함.
`$ java -jar gof.jar a.txt 10`

## Errors

argument in file error - first line only has width height.(-1).
argument in file error - first line,width height has to be int.(-2).
argument in file error - need to 'width >=80, height >=40'(-3).
argument in file error - second line only has cell count.(-4).
argument in file error - second line,cell count has to be int.(-5).
argument in file error - thrid line only has cell's index.(-6).
input file error occur. file lines under 2.(-7).
input file error occur. cell count and cell location are not match(-8).
input file error occur: input cell location has to be int.(-9).
input file error occur: input cell location has invalidate value.(-10).
input file error occur: {x},{u} cell is not exists in grid.(-11).
argument error: argument only has one or two.(-12).
argument error: second argument has to be int.(-13).
error occur when file save.(-14).
오류 발생시 위 오류코드로 문의 주세요.

## Installation(ubuntu)

ㅇ 자바설치
java가 설치되어 있으면 아래 내용을 수행할 필요가 없습니다.
1.jdk 다운로드
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. install-java.sh script 수행, root권한이 필요하며, jdk파일이 필요합니다.
`$ sudo ./install-java.sh  ./jdk-8u231-linux-x64.tar.gz`
3. 설치 완료

## execute
a.txt는 샘플파일이며, 샘플파일을 토대로 다른 preset을 생성할 수 있습니다.
`$ java -jar gof.jar`
`$ java -jar gof.jar a.txt`
`$ java -jar gof.jar a.txt 10`
