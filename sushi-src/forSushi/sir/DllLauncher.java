package forSushi.sir;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sir/dll/DoubleLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sir/dll/DoubleLinkedList.*");
		super.configureSushiScope(p, "sir/dll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sir/sir-dll.jbse"));
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi("[forSushi]sir.dll.txt");
	}

}
