package examples.clients.buildtools;

import com.github.dockerjava.api.command.AuthCmd;
import org.apache.bookkeeper.tools.framework.Cli;
import org.apache.bookkeeper.tools.framework.CliSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 11:00 2022/2/17
 @Modified By:
 **********************************/
public abstract class ExampleRunner <T extends PulsarClientFlags>  {
    private static final Logger log = LoggerFactory.getLogger(ExampleRunner.class);
    protected abstract String name();
    protected abstract String description();
    protected abstract T flags();
    protected abstract void run(T flags) throws Exception;
    public void run(String[] args){
        CliSpec.Builder<T> speBuilder = CliSpec.<T>newBuilder()
                .withName(name())
                .withDescription(description())
                .withFlags(flags())
                .withRunFunc(flags ->{
                    try{
                        run(flags);
                        return true;
                    } catch (Exception e){
                        log.error("Failed to execute example `{}`",name(),e);
                        System.err.println("Failed to execute example`"+name()+"`");
                        e.printStackTrace();
                        return false;
                    }
                });
        CliSpec<T> spec = speBuilder.build();
        int retCode = Cli.runCli(spec,args);
        Runtime.getRuntime().exit(retCode);
    }

}
