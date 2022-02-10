package org.example;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttachMain {
    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException, InterruptedException {
        String pid = getPid(args[0]);
        if (pid == null) {
            System.out.println("不存在【" + args[0] + "】的进程");
            return;
        }
        VirtualMachine vm = VirtualMachine.attach(pid);//args[0]传入的是jvm的pid号
        vm.loadAgent("agent-01\\target\\agent.jar");
        vm.detach();
    }

    public static String getPid(String expectProcessName) {
        List<String> list = new ArrayList<String>();
        try {
            // 获取监控主机
            MonitoredHost local;
            local = MonitoredHost.getMonitoredHost("localhost");
            // 取得所有在活动的虚拟机集合
            Set<Object> vmlist = new HashSet<Object>(local.activeVms());
            // 遍历集合，输出PID和进程名
            for (Object process : vmlist) {
                MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
                // 获取类名
                String processname = MonitoredVmUtil.mainClass(vm, true);
                if (processname.contains(expectProcessName)) {
                    return process + "";
                }
            }
        } catch (MonitorException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
