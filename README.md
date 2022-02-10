# java-agent-example
执行
> mvn clean package
## premain
设置javaagent
![设置javaagent](doc/1.png)
运行效果
![](doc/2.png)
## agentmain
先运行DeadLoopMain，然后运行AttachMain,运行效果
![](doc/3.png)