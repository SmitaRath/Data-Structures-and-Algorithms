################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../bs_tree.cpp \
../main.cpp \
../random_generator.cpp \
../rb_tree.cpp \
../sort.cpp \
../timer.cpp 

OBJS += \
./bs_tree.o \
./main.o \
./random_generator.o \
./rb_tree.o \
./sort.o \
./timer.o 

CPP_DEPS += \
./bs_tree.d \
./main.d \
./random_generator.d \
./rb_tree.d \
./sort.d \
./timer.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -O2 -g -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


