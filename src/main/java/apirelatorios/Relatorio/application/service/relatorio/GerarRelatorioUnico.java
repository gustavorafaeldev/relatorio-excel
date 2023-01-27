package apirelatorios.Relatorio.application.service.relatorio;

import apirelatorios.Relatorio.application.service.ContatosCampanhaService;
import apirelatorios.Relatorio.domain.entity.ContatosCampanha;
import apirelatorios.Relatorio.utils.DateConverter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GerarRelatorioUnico {

    private final DateConverter dateConverter;

    public ResponseEntity<Void> gerarArquivo(String datas
            , String type, HttpServletResponse httpServletResponse,
                                             ContatosCampanhaService service) {

        httpServletResponse.setHeader
                ("Content-disposition"
                        , "attachment; filename=" + System.currentTimeMillis() + "_Simplificado." + type);

        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

        XSSFCellStyle my_style = (XSSFCellStyle) workbook.createCellStyle();
        XSSFFont my_font = (XSSFFont) workbook.createFont();
        my_font.setBold(true);
        my_style.setFont(my_font);

        String[] listDatas = datas.split(",");


        for (String dat : listDatas) {

            SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(dat);
            int numeroLinha = 1;

            dateConverter.setInitalAndFinalDate(dat);

            List<ContatosCampanha> listFilter = service.campanhaService.findByCampanhaidAndDataimportacaoBetween(
                    service.request.getCampanhaid(), dateConverter.getDateInit(), dateConverter.getDataEnd());


            for (ContatosCampanha campanha : listFilter) {
                Row row;
                for (String contatos : campanha.getContacts()) {
                    row = sheet.createRow(numeroLinha++);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(contatos);
                }
            }

            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Telefones");
            cell.setCellStyle(my_style);

            sheet.autoSizeColumn(0);

        }

        try {
            workbook.write(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
