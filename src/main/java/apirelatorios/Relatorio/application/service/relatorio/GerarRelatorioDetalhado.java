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
public class GerarRelatorioDetalhado {

    private final DateConverter dateConverter;


    public ResponseEntity<?> gerarArquivo(String datas, String type
            , HttpServletResponse httpServletResponse
            , ContatosCampanhaService service) {

        httpServletResponse.setHeader
                ("Content-disposition", "attachment; filename=" + System.currentTimeMillis() + "_Detalhado." + type);

        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);


        XSSFCellStyle my_style = (XSSFCellStyle) workbook.createCellStyle();
        XSSFFont my_font = (XSSFFont) workbook.createFont();
        my_font.setBold(true);
        my_style.setFont(my_font);

        String[] listDatas = datas.split(",");


        //Loop em cada data
        for (String dat : listDatas) {

            SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(dat);
            int contadorCampanha = 0;
            int rownum = 0;

            dateConverter.setInitalAndFinalDate(dat);

            List<ContatosCampanha> listFiter = service.campanhaService.findByCampanhaidAndDataimportacaoBetween(
                    service.request.getCampanhaid(), dateConverter.getDateInit(), dateConverter.getDataEnd());

            // Preenche nomes dos grupos
            int posicao = 0;
            for (ContatosCampanha campanha : listFiter) {
                Row row;
                contadorCampanha++;
                if (contadorCampanha == 1) {
                    row = sheet.createRow(rownum++);
                } else {
                    row = sheet.getRow(rownum++);
                }
                if (row == null) {
                    rownum--;
                    row = sheet.createRow(rownum++);
                }

                Cell cell = row.createCell(posicao);
                cell.setCellValue(campanha.getNamegroup());
                cell.setCellStyle(my_style);

                posicao++;
                rownum = 0;
            }

            //Preenche contatos dos grupos
            int posi = 0;
            int contCampanha = 0;
            for (ContatosCampanha campanha : listFiter) {
                int numeroLinha = 1;
                contCampanha++;

                Row row;
                for (String contatos : campanha.getContacts()) {
                    if (contCampanha == 1) {
                        row = sheet.createRow(numeroLinha++);
                    } else {
                        row = sheet.getRow(numeroLinha++);
                    }
                    if (row == null) {
                        numeroLinha--;
                        row = sheet.createRow(numeroLinha++);
                    }
                    Cell cell = row.createCell(posi);
                    cell.setCellValue(contatos);
                }
                sheet.autoSizeColumn(posi);
                posi++;
            }
        }

        try {
            workbook.write(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}