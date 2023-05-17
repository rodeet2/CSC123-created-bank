import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.usman.csudh.bank.conversionfilereaderonline;
import com.usman.csudh.bank.settings;

import java.io.File;

public class getfilefrom_local_hook extends conversionfilereaderonline{

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(new settings().getCurrency_file());
	}




}