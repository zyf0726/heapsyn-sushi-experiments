package forSushi.sir.dll;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/sir/dll/DoubleLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/sir/dll/DoubleLinkedList.*");
		super.configureScope(p, "forSushi/sir/dll/DoubleLinkedList$Entry");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/sir/sir-dll.jbse"));
		p.setDepthScope(20);
		p.setCountScope(100);
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi();
	}

}
