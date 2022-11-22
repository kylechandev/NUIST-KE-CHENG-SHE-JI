<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:o="urn:schemas-microsoft-com:office:office"
          xmlns:x="urn:schemas-microsoft-com:office:excel"
          xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:html="http://www.w3.org/TR/REC-html40">
    <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
        <Author>范海腾</Author>
        <LastAuthor>范海腾</LastAuthor>
        <Created>2015-06-05T18:19:34Z</Created>
        <LastSaved>2020-02-18T06:43:46Z</LastSaved>
        <Version>16.00</Version>
    </DocumentProperties>
    <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
        <AllowPNG/>
    </OfficeDocumentSettings>
    <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
        <WindowHeight>12645</WindowHeight>
        <WindowWidth>22260</WindowWidth>
        <WindowTopX>32767</WindowTopX>
        <WindowTopY>32767</WindowTopY>
        <ProtectStructure>False</ProtectStructure>
        <ProtectWindows>False</ProtectWindows>
    </ExcelWorkbook>
    <Styles>
        <Style ss:ID="Default" ss:Name="Normal">
            <Alignment ss:Vertical="Bottom"/>
                                            <Borders/>
<Font ss:FontName="等线" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
                                                        <Interior/>
                                                                  <NumberFormat/>
                                                                                <Protection/>
</Style>
        <Style ss:ID="s63">
            <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
        </Style>
        <Style ss:ID="s64">
            <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
                                                                   <Interior ss:Color="#FFFF00" ss:Pattern="Solid"/>
        </Style>
        <Style ss:ID="s68">
            <Alignment ss:Vertical="Center"/>
                                            <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"/>
        </Style>
        <Style ss:ID="s69">
            <Alignment ss:Vertical="Center"/>
                                            <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9" ss:Color="#000000"
            ss:Bold="1"/>
        </Style>
        <Style ss:ID="s70">
            <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
        </Style>
        <Style ss:ID="s72">
            <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
        </Style>
    </Styles>
    <Worksheet ss:Name="Sheet1">
        <Table ss:ExpandedRowCount="9999" ss:ExpandedColumnCount="7"  x:FullColumns="1"
               x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="14.25">
        <#list list as l>
            <Row>
                <Cell ss:MergeAcross="5" ss:StyleID="s64"><Data ss:Type="String">比赛成绩表</Data></Cell>
            </Row>
            <0000>
                <Cell ss:MergeAcross="1" ss:StyleID="s63"><Data ss:Type="String">${l.name}</Data></Cell>
            </0000>
            <Row>
                <Cell ss:StyleID="s69"><Data ss:Type="String">比赛名称</Data></Cell>
                <Cell ss:StyleID="s69"><Data ss:Type="String">人员</Data></Cell>
                <Cell ss:StyleID="s69"><Data ss:Type="String">班级</Data></Cell>
                <Cell ss:StyleID="s69"><Data ss:Type="String">成绩</Data></Cell>
                <Cell ss:StyleID="s69"><Data ss:Type="String">分数</Data></Cell>
            </Row>
            <#if l.ll??>
            <#list l.ll as lll>
                <Row>
                    <Cell ss:StyleID="s68"><Data ss:Type="String">${l.name}</Data></Cell>
                    <Cell ss:StyleID="s68"><Data ss:Type="String">${lll.realname}</Data></Cell>
                    <Cell ss:StyleID="s68"><Data ss:Type="String">${lll.team}</Data></Cell>
                    <Cell ss:StyleID="s68"><Data ss:Type="String">${lll.grade}</Data></Cell>
                    <Cell ss:StyleID="s68"><Data ss:Type="String">${lll.score}</Data></Cell>
                </Row>
            </#list>
            </#if>
            <Row>
                <Cell ss:MergeAcross="5" ss:StyleID="s70"><Data ss:Type="String" x:Ticked="1">**********************************************************</Data></Cell>
                <Cell ss:StyleID="s72"/>
            </Row>
        </#list>
        </Table>
        <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
            <PageSetup>
                <Header x:Margin="0.3"/>
                <Footer x:Margin="0.3"/>
                <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
            </PageSetup>
            <Print>
                <ValidPrinterInfo/>
                <PaperSizeIndex>9</PaperSizeIndex>
                <HorizontalResolution>600</HorizontalResolution>
                <VerticalResolution>600</VerticalResolution>
            </Print>
            <Selected/>
            <Panes>
                <Pane>
                    <Number>3</Number>
                    <ActiveRow>4</ActiveRow>
                    <ActiveCol>4</ActiveCol>
                </Pane>
            </Panes>
            <ProtectObjects>False</ProtectObjects>
            <ProtectScenarios>False</ProtectScenarios>
        </WorksheetOptions>
    </Worksheet>
</Workbook>
