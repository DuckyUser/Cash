package ml.lbplugins.cash.manager;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import ml.lbplugins.cash.Main;
import ml.lbplugins.cash.shop.SelectOption;

public class ClanPlaceholder {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public ClanPlaceholder(Main plugin) {
		this.plugin = plugin;
		registerPlaceholders();
	}

	@SuppressWarnings("deprecation")
	private void registerPlaceholders() {
        PlaceholderAPI.registerPlaceholderHook("lbplugin", new PlaceholderHook() {
            @Override
            public String onRequest(OfflinePlayer p, String params) {
                if(p!=null && p.isOnline()){
                    return onPlaceholderRequest(p.getPlayer(),params);
                }
                return null;
            }

            @Override
            public String onPlaceholderRequest(Player p, String params) {
                if(p==null){
                    return null;
                }
                // %tscplugin_PARAMS%
                if(params.equalsIgnoreCase("cash")){
                	if (Main.cash.get(p.getName())==null) {
                		SelectOption.debug(p);
                	}
                    return "" 	+ Main.cash.get(p.getName());
                }
                return null;
            }
        });
    }
	
	
}