#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux-x86
CND_DLIB_EXT=so
CND_CONF=Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/_ext/88668595/histColor.o \
	${OBJECTDIR}/_ext/979907916/ejecucion.o \
	${OBJECTDIR}/_ext/979907916/gpu-basics-similarity.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/pso-clahe-color-opencv

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/pso-clahe-color-opencv: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.cc} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/pso-clahe-color-opencv ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/_ext/88668595/histColor.o: /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/OpenCV-Entropy-master/histColor.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/88668595
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/88668595/histColor.o /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/OpenCV-Entropy-master/histColor.cpp

${OBJECTDIR}/_ext/979907916/ejecucion.o: /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/ejecucion.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/979907916
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/979907916/ejecucion.o /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/ejecucion.cpp

${OBJECTDIR}/_ext/979907916/gpu-basics-similarity.o: /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/gpu-basics-similarity.cpp 
	${MKDIR} -p ${OBJECTDIR}/_ext/979907916
	${RM} "$@.d"
	$(COMPILE.cc) -O2 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/_ext/979907916/gpu-basics-similarity.o /home/lg_more/MEGAsync/maestria/tesis/implementacion-paper/pso-ce-color.git/pso-clahe-color-opencv/gpu-basics-similarity.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/pso-clahe-color-opencv

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
