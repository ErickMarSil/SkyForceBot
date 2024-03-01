package com.erick.SkyForce;

import com.erick.SkyForce.Listener.Events;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class SkyForceClass {

    private final Dotenv venv;
    private final ShardManager shardM;
    public SkyForceClass(){
        venv = Dotenv.configure().load();
        DefaultShardManagerBuilder build = DefaultShardManagerBuilder.createDefault(getToken());
        shardM = setConfigsBuild(build);
        shardM.addEventListener(new Events());
    }
    private ShardManager setConfigsBuild(DefaultShardManagerBuilder build) {
        build.setStatus(OnlineStatus.ONLINE);
        build.setActivity(Activity.customStatus(venv.get("MESSAGE")));
        return build.build();
    }
    private String getToken(){
        return venv.get("TOKEN");
    }
    public static void main(String[] args){
        SkyForceClass SKFClass = new SkyForceClass();
    }
}
