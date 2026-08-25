// ============================================================================
// Astah Script Editor - Gerador Automático de Diagrama de Sequência
// Linguagem: ECMAScript (JavaScript)
// Compatibilidade: Nashorn (Java 8+) & Rhino (Versões anteriores do Astah)
// ============================================================================

// Helper para obter a classe Point2D.Double de acordo com a engine JavaScript ativa (Nashorn ou Rhino)
function makePoint(x, y) {
    var Point2DDouble;
    if (typeof Java !== 'undefined' && Java.type) {
        try {
            // Em Nashorn, classes internas estáticas usam "$" para separação
            Point2DDouble = Java.type('java.awt.geom.Point2D$Double');
        } catch (e) {
            Point2DDouble = Java.type('java.awt.geom.Point2D.Double');
        }
    } else {
        // Fallback para Rhino (versões antigas do Astah)
        Point2DDouble = Packages.java.awt.geom.Point2D.Double;
    }
    return new Point2DDouble(x, y);
}

function run() {
    // Utiliza o objeto global 'astah' diretamente, que é o ProjectAccessor no Script Editor
    var projectAccessor = astah;
    var rootModel = projectAccessor.getProject();

    if (rootModel == null) {
        print("Erro: Nenhum projeto aberto no Astah. Por favor, abra ou crie um projeto primeiro.");
        return;
    }

    var diagramEditorFactory = projectAccessor.getDiagramEditorFactory();
    var de = diagramEditorFactory.getSequenceDiagramEditor();
    var transactionManager = projectAccessor.getTransactionManager();

    print("Iniciando transação para criação do Diagrama de Sequência...");
    transactionManager.beginTransaction();

    try {
        // 1. Criar o Diagrama de Sequência no modelo raiz do projeto
        var name = "Diagrama de Sequência - Sistema Integrado";
        var diagram = de.createSequenceDiagram(rootModel, name);
        de.setDiagram(diagram);
        print("Diagrama '" + name + "' criado com sucesso.");

        // 2. Criar e posicionar as Linhas de Vida (Lifelines) horizontalmente (X)
        // Espaçamento ampliado para 250px para evitar sobreposição horizontal dos nomes
        var u   = de.createLifeline("Usuário / Cliente", 100);
        var m   = de.createLifeline(":Main", 350);
        var c   = de.createLifeline("catalogo :Catalogo", 600);
        var ne  = de.createLifeline("notebook :ProdutoEletronico", 850);
        var al  = de.createLifeline("arroz :ProdutoAlimenticio", 1100);
        var p   = de.createLifeline("pedido :Pedido", 1350);
        var px  = de.createLifeline("pix :Pix", 1600);
        var ca  = de.createLifeline("cartao :Cartao", 1850);
        var ts  = de.createLifeline("GerenciadorTimestamp", 2100);
        print("Linhas de vida criadas e espaçadas.");

        // 3. Mapeamento das Mensagens e Retornos verticalmente (Y)
        // Espaçamento vertical (gap) definido para 70px para evitar sobreposição vertical dos textos
        var y = 100;
        var gap = 70;

        // FASE 1: Inicializando Módulo de Produtos
        var msgExec = de.createMessage("executar()", u, m, y);
        y += gap;

        var msgNewCat = de.createMessage("new Catalogo()", m, c, y);
        y += gap;
        de.createReturnMessage("catalogo", msgNewCat);
        y += gap;

        var msgNewNE = de.createMessage("new ProdutoEletronico(\"E001\", \"Notebook Gamer\", 5000.00, 24)", m, ne, y);
        y += gap;
        de.createReturnMessage("notebook", msgNewNE);
        y += gap;

        var msgNewAL = de.createMessage("new ProdutoAlimenticio(\"A001\", \"Arroz 5kg\", 25.90, \"31/12/25\")", m, al, y);
        y += gap;
        de.createReturnMessage("arroz", msgNewAL);
        y += gap;

        var msgAddNE = de.createMessage("adicionar(notebook)", m, c, y);
        y += gap;
        de.createReturnMessage("void", msgAddNE);
        y += gap;

        var msgAddAL = de.createMessage("adicionar(arroz)", m, c, y);
        y += gap;
        de.createReturnMessage("void", msgAddAL);
        y += gap;

        var msgList = de.createMessage("listarProdutos()", m, c, y);
        y += gap;

        // Fragmento Combinado: Loop para exibição de produtos
        var loopStart = y - 20;
        var msgStrNE = de.createMessage("toString()", c, ne, y);
        y += gap;
        de.createReturnMessage("stringRepresentacao", msgStrNE);
        y += gap;

        var msgStrAL = de.createMessage("toString()", c, al, y);
        y += gap;
        de.createReturnMessage("stringRepresentacao", msgStrAL);
        y += gap;
        var loopEnd = y - 10;

        // Envolve as chamadas toString no fragmento LOOP
        // X = Catalogo (600) - 40 = 560
        // Largura = Arroz (1100) - Catalogo (600) + 80 = 580
        var loopLoc = makePoint(560, loopStart);
        de.createCombinedFragment("para cada produto na lista", "loop", loopLoc, 580, (loopEnd - loopStart));

        de.createReturnMessage("void", msgList);
        y += gap;

        // FASE 2: Criando o Pedido
        var msgNewPed = de.createMessage("new Pedido()", m, p, y);
        y += gap;
        de.createReturnMessage("pedido", msgNewPed);
        y += gap;

        var msgAddPedNE = de.createMessage("adicionarProduto(notebook)", m, p, y);
        y += gap;
        de.createReturnMessage("void", msgAddPedNE);
        y += gap;

        var msgAddPedAL = de.createMessage("adicionarProduto(arroz)", m, p, y);
        y += gap;
        de.createReturnMessage("void", msgAddPedAL);
        y += gap;

        // FASE 3: Processando Pagamento
        var msgNewPix = de.createMessage("new Pix(10000.00)", m, px, y);
        y += gap;
        var msgTsPix = de.createMessage("getTimestampFormatado()", px, ts, y);
        y += gap;
        de.createReturnMessage("dataHoraLimpa", msgTsPix);
        y += gap;
        de.createReturnMessage("pix", msgNewPix);
        y += gap;

        var msgNewCard = de.createMessage("new Cartao(6000.00)", m, ca, y);
        y += gap;
        var msgTsCard = de.createMessage("getTimestampFormatado()", ca, ts, y);
        y += gap;
        de.createReturnMessage("dataHoraLimpa", msgTsCard);
        y += gap;
        de.createReturnMessage("cartao", msgNewCard);
        y += gap;

        var msgSetPay = de.createMessage("setMeioPagamento(pix)", m, p, y);
        y += gap;
        de.createReturnMessage("void", msgSetPay);
        y += gap;

        var msgFin = de.createMessage("finalizarPedido()", m, p, y);
        y += gap;

        // Fragmento Combinado: ALT para fluxo de pagamento
        var finStart = y - 20;

        var msgCalc = de.createMessage("calcularTotal()", p, p, y);
        y += 75; // maior espaçamento para chamadas internas auto-referenciadas
        var msgGetP1 = de.createMessage("getPreco()", p, ne, y);
        y += gap;
        de.createReturnMessage("5000.00", msgGetP1);
        y += gap;

        var msgGetP2 = de.createMessage("getPreco()", p, al, y);
        y += gap;
        de.createReturnMessage("25.90", msgGetP2);
        y += gap;
        de.createReturnMessage("5025.90", msgCalc);
        y += gap;

        var msgPay = de.createMessage("pagar(5025.90)", p, px, y);
        y += gap;
        var msgTax = de.createMessage("taxaAplicada()", px, px, y);
        y += 75;
        de.createReturnMessage("0.0", msgTax);
        y += gap;

        var msgRec = de.createMessage("recibo(5025.90)", px, px, y);
        y += 75;
        var msgTax2 = de.createMessage("taxaAplicada()", px, px, y);
        y += 75;
        de.createReturnMessage("0.0", msgTax2);
        y += gap;
        de.createReturnMessage("stringRecibo", msgRec);
        y += gap;

        de.createReturnMessage("void", msgPay);
        y += gap;

        de.createReturnMessage("void", msgFin);
        y += gap;
        var finEnd = y - 10;

        // Cria o bloco ALT englobando as mensagens de finalização de pedido e pagamento
        // X = Notebook (850) - 40 = 810
        // Largura = Timestamp (2100) - Notebook (850) + 80 = 1330
        var altLoc = makePoint(810, finStart);
        de.createCombinedFragment("fluxo normal", "alt", altLoc, 1330, (finEnd - finStart));

        de.createReturnMessage("void", msgExec);
        
        transactionManager.endTransaction();
        print("Diagrama de Sequência criado e organizado com sucesso no Astah!");
    } catch (e) {
        if (transactionManager != null) {
            try {
                transactionManager.abortTransaction();
            } catch (ex) {
                print("Erro ao abortar transação: " + ex);
            }
        }
        print("Erro durante a execução do script: " + e);
        if (e.stack) {
            print(e.stack);
        }
    }
}

run();
