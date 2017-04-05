package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {

	public Map<String, ProcessingMethod> methodMap;

	public CommandFactory(List<String> commands) {

		this.methodMap = new HashMap<String, ProcessingMethod>();

		for (String string : commands) {
			this.methodMap.put(string, new ProcessingMethod() {
				public String method() {
					return string;
				}
			});
		}

	}

}
