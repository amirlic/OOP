cmake_minimum_required(VERSION 3.3)

project(SerialCmake)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -pthread")

set(BOOST_ROOT CACHE PATH "/usr/include/boost/")
find_package(Boost 1.58.0 COMPONENTS serialization system)
include_directories(${Boost_INCLUDE_DIR})

add_executable(main_test main.cpp)

target_link_libraries(main_test ${Boost_LIBRARIES})