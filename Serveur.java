import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Serveur {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLocation(100,100);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		System.out.println("zob 1");
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.white);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(canvas) ;
		frame.add(panel) ;
		System.out.println("zob 2");
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"/usr/bin/vlc");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		MediaPlayerFactory mpfFactory = new MediaPlayerFactory();
		EmbeddedMediaPlayer empEmbeddedMediaPlayer = mpfFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(frame));
		empEmbeddedMediaPlayer.setVideoSurface(mpfFactory.newVideoSurface(canvas));
		empEmbeddedMediaPlayer.toggleFullScreen();
		empEmbeddedMediaPlayer.setEnableMouseInputHandling(false);
		empEmbeddedMediaPlayer.setEnableKeyInputHandling(true);
		
		empEmbeddedMediaPlayer.prepareMedia("test.mp4");
		empEmbeddedMediaPlayer.play();
	}
}
