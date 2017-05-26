#
# Oracle Java 8 Dockerfile
#
# https://github.com/cogniteev/docker-oracle-java
# https://github.com/cogniteev/docker-oracle-java/tree/master/oracle-java8
#

# Pull base image.
FROM ubuntu:16.04

# Install Java.
RUN \
  apt-get update && \
  apt-get install -y software-properties-common && \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

#crear directorio de trabajo
RUN \
	mkdir /home/lg_more

#instalar opencv
RUN \
# KEEP UBUNTU OR DEBIAN UP TO DATE

	 apt -y update && \
	 apt -y upgrade && \
	 apt -y dist-upgrade && \
	 apt -y autoremove && \


	# INSTALL THE DEPENDENCIES

	# Build tools:
	 apt install -y build-essential cmake && \

	# GUI (if you want to use GTK instead of Qt, replace 'qt5-default' with 'libgtkglext1-dev' and remove '-DWITH_QT=ON' option in CMake):
	 apt install -y qt5-default libvtk6-dev && \

	# Media I/O:
	 apt install -y zlib1g-dev libjpeg-dev libwebp-dev libpng-dev libtiff5-dev libjasper-dev libopenexr-dev libgdal-dev && \

	# Video I/O:
	 apt install -y libdc1394-22-dev libavcodec-dev libavformat-dev libswscale-dev libtheora-dev libvorbis-dev libxvidcore-dev libx264-dev yasm libopencore-amrnb-dev libopencore-amrwb-dev libv4l-dev libxine2-dev && \

	# Parallelism and linear algebra libraries:
	 apt install -y libtbb-dev libeigen3-dev && \

	# Python:
	 apt install -y python-dev python-tk python-numpy python3-dev python3-tk python3-numpy && \

	# Java:
	 #apt-get install -y ant default-jdk && \

	# Documentation:
	 apt install -y doxygen 


	# INSTALL THE LIBRARY (YOU CAN CHANGE '3.2.0' FOR THE LAST STABLE VERSION)

	RUN apt install -y unzip wget && \
		 wget https://github.com/opencv/opencv/archive/3.2.0.zip && \ 
		 unzip 3.2.0.zip && \
		 rm 3.2.0.zip && \
		 mv opencv-3.2.0 OpenCV && \
		 cd OpenCV && \
		 mkdir build && \
		 cd build && \
		 cmake -DWITH_QT=ON -DWITH_OPENGL=ON -DFORCE_VTK=ON -DWITH_TBB=ON -DWITH_GDAL=ON -DWITH_XINE=ON -DBUILD_EXAMPLES=ON .. && \
		 make -j4 && \
		 make install && \
		 ldconfig 

# Define working directory.
WORKDIR /home/lg_more

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Define default command.
CMD ["bash"]