# test-emq
听说EMQ有问题，所以我写了测试程序进行测试，运行publisher进行测试，然后用mqtt-spy接收数据。目前发现，32个通道，间隔50毫秒，每次发送1024字节是没有问题的。如果出现mqtt-spy程序停止接收，请关闭电脑上其它无用的软件，因为这会影响mqtt-spy接收数据。

程序打包的方式很简单，进入publisher目录，运行`mvn clean install`就行了，然后将jar包和yml文件放一起，想改参数可以改yml文件