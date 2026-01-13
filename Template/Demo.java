import java.util.Map;

// creating the abstract base class
abstract class AbstractReportExporter {
    public final void exportReport(ReportData data, String filePath) {
        prepareData(data);
        openFile(filePath);
        writeHeader(data);
        writeDataRows(data);
        writeFooter(data);
        closeFile(filePath);
        System.out.println("Export complete: " + filePath);
    }

    protected void prepareData(ReportData data) { // Hook method
        System.out.println("Preparing report data (common step)...");
    }

    protected void openFile(String filePath) { // Hook method
        System.out.println("Opening file '" + filePath);
    };

    protected abstract void writeHeader(ReportData data);

    protected abstract void writeDataRows(ReportData data);

    protected void writeFooter(ReportData data) { // Hook method
        System.out.println("Writing footer (default: no footer).");
    }

    protected void closeFile(String filePath) { // Hook method
        System.out.println("Closing file '" + filePath);
    };
}

// implementing the concrete class
class CsvReportExporter extends AbstractReportExporter {
    

    @Override
    protected void writeHeader(ReportData data) {
        System.out.println("CSV: Writing header: " + String.join(",", data.getHeaders()));
    }

    @Override
    protected void writeDataRows(ReportData data) {
        System.out.println("CSV: Writing data rows...");
        for (Map<String, Object> row : data.getRows()) {
            System.out.println("CSV: " + row.values());
        }
    }
}

class PdfReportExporter extends AbstractReportExporter {
    //prepareData() not overridden - default will be used
    //openFile() not overridden - default will be used

    @Override
    protected void writeHeader(ReportData data) {
        System.out.println("PDF: Writing header: " + String.join(",", data.getHeaders()));
    }

    @Override
    protected void writeDataRows(ReportData data) {
        System.out.println("PDF: Writing data rows...");
        for (Map<String, Object> row : data.getRows()) {
            System.out.println("PDF: " + row.values());
        }
    }

    // writeFooter() not overridden - default will be used
    // closeFile() not overridden - default will be used
}

public class Demo {
    public static void main(String[] args) {
        ReportData data = new ReportData();

        AbstractReportExporter csvExporter = new CsvReportExporter();
        csvExporter.exportReport(data, "sales_report");

        System.out.println();

        AbstractReportExporter pdfExporter = new PdfReportExporter();
        pdfExporter.exportReport(data, "financial_summary");
    }
}