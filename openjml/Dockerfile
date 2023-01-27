FROM ubuntu:20.04
RUN apt-get update && apt-get install -y curl libgomp1 unzip
RUN mkdir "/jml"
COPY openjml.zip /jml/jml.zip
RUN unzip '/jml/jml.zip' -d '/jml' && rm '/jml/jml.zip'
