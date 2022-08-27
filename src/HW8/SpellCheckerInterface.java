package HW8;

import java.io.FileNotFoundException;
import java.util.*;

public interface SpellCheckerInterface
{
	public List<String> getIncorrectWords(String filename) throws FileNotFoundException;
	public Set<String> getSuggestions(String word);
}
