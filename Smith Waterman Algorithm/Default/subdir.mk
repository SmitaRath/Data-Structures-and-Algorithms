################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../dynprog.cpp \
../main.cpp \
../random_generator.cpp \
../timer.cpp 

OBJS += \
./dynprog.o \
./main.o \
./random_generator.o \
./timer.o 

CPP_DEPS += \
./dynprog.d \
./main.d \
./random_generator.d \
./timer.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -O2 -g -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


