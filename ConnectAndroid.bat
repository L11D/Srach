@echo off
cd "C:\Users\Liid\Desktop\platform-tools"
adb tcpip 5555
adb connect 192.168.0.105:5555
pause