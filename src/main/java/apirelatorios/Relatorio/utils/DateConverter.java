package apirelatorios.Relatorio.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
@Data
public class DateConverter {

    private Long dateInit;
    private Long dataEnd;

    public void setInitalAndFinalDate(String dat) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDateTime dateTime = LocalDate.parse(dat, formatter).atStartOfDay();
        dateInit = dateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant().getEpochSecond() * 1000;
        

        Date dtVal;
		try {
			dtVal = new SimpleDateFormat("dd-MM-yyyy").parse(dat);
			
			GregorianCalendar cValidacaotask = new GregorianCalendar();
			cValidacaotask.add(Calendar.DATE, 1);
			dtVal.setTime(cValidacaotask.getTimeInMillis());
			
			dataEnd = dtVal.getTime();
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        //dataEnd = dateTime.plusDays(1).atZone(ZoneId.of("America/Sao_Paulo")).toInstant().getEpochSecond() * 1000;
    }
}
