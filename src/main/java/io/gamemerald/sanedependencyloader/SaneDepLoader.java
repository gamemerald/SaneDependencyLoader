package io.gamemerald.sanedependencyloader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaneDepLoader implements PreLaunchEntrypoint {
	public static final Logger LOGGER = LoggerFactory.getLogger("Sane Dependency Loader");
    public static int showWindow = 0;

	private void popupWindow(){
		JFrame frame = new JFrame("Sane Dependency Loader");
		JPanel p = new JPanel();
		JLabel emptyLabel = new JLabel("Mods have been newly added please relaunch game!", SwingConstants.CENTER);

		JButton b = new JButton("Ok");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		p.add(emptyLabel);
		p.add(b);

		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public void downloadFile(String url){
		try {
			String fixedUrl = url.substring(1, url.length() - 1);
			URL u = new URL(fixedUrl);
			String name = FilenameUtils.getName(u.getPath());
			String path = System.getProperty("user.dir") + "/mods/" + name;
			if(Files.exists(new File(path).toPath())){
				LOGGER.info("Skipped Download of: " + name);
			}else{
				LOGGER.info("Attempting to download " + name + " at " + u.toString());
				InputStream in = u.openStream();
				Files.copy(in, Paths.get(path));
				showWindow = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download(){
		try{
			if(Files.exists(new File("depends.json").toPath())){
				Gson gson = new Gson();
				JsonParser parser = new JsonParser();
				JsonObject object = (JsonObject) parser.parse(Files.readString(Paths.get("depends.json")));// response will be the json String

				JsonObject json = object;
				json.toString();

				for (String s : json.keySet()) {
					downloadFile(json.get(s).toString());
				}
			}else{
				LOGGER.error("Modpack Author Forgot to include depends.json");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public int getShowWindow() {
		return showWindow;
	}

	@Override
	public void onPreLaunch() {
		download();
		if(showWindow == 1){
			popupWindow();
		}
	}
}
