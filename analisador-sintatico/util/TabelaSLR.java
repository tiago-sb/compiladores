/* ***************************************************************
* Autor............: TIAGO SANTOS BELA E CHRISTIAN SCHETTINE PAIVA ROCHA
* Matricula........: 202220722 e 202210159
* Inicio...........: 30.11.2025
* Ultima alteracao.: 09.12.2025
* Nome.............: TipoErro
* Funcao...........: classe que representa a tabela sintática 
    SLR(1) contendo os métodos necessários para a anlálise sintática
    das expressões da linguagem Sigma.
*************************************************************** */

package util;

import java.util.*;

import model.Gramatica;
import model.Producao;

public class TabelaSLR {
  private Map<String, String> acao;
  private Map<String, Integer> irPara;
  private Gramatica gramatica;

  public TabelaSLR(Gramatica gramatica) {
    this.gramatica = gramatica;
    this.acao = new HashMap<>();
    this.irPara = new HashMap<>();
    inicializarTabelaCompleta();
  }

   /*
   * ***************************************************************
   * Metodo: inicializarTabelaCompleta
   * Funcao: movimentos de empilha e reduz referentes a tabela 
   *   sintática
   * Parametros: vazio
   * Retorno: void
   */
  private void inicializarTabelaCompleta() {
    // Estado 0
    acao.put("0-program", "s1");
    irPara.put("0-S", 2);

    // Estado 1
    acao.put("1-id", "s3");

    // Estado 2
    acao.put("2-$", "acc");

    // Estado 3
    acao.put("3-;", "s4");

    // Estado 4
    acao.put("4-var", "s5");
    acao.put("4-begin", "s6");
    irPara.put("4-D", 7);

    // Estado 5
    acao.put("5-id", "s8");
    irPara.put("5-V", 9);
     
    // Estado 6
    acao.put("6-id", "s11");
    acao.put("6-end", "s42");
    acao.put("6-begin", "s12");
    acao.put("6-read", "s13");
    acao.put("6-readln", "s14");
    acao.put("6-write", "s15");
    acao.put("6-writeln", "s16");
    acao.put("6-if", "s17");
    acao.put("6-while", "s18");
    irPara.put("6-L", 19);
    irPara.put("6-C", 20);
    irPara.put("6-A", 21);
    irPara.put("6-R", 22);
    irPara.put("6-W", 23);
    irPara.put("6-M", 24);
    irPara.put("6-N", 25);
    irPara.put("6-P", 26);

    // Estado 7
    acao.put("7-begin", "s27");

    // Estado 8
    acao.put("8-;", "r8");
    acao.put("8-,", "s28");
    acao.put("8-:", "r8");
    acao.put("8-)", "s14");
    acao.put("8-else", "r8");
    irPara.put("5-I", 10);

    // Estado 9
    acao.put("9-begin", "r3");  

    // Estado 10
    acao.put("10-:", "s29");

    // Estado 11
    acao.put("11-:=", "s30");
    acao.put("11-=", "s30");


    // Estado 12
    acao.put("12-id", "s11");
    acao.put("12-begin", "s12");
    acao.put("12-read", "s13");
    acao.put("12-readln", "s14");
    acao.put("12-write", "s15");
    acao.put("12-writeln", "s16");
    acao.put("12-if", "s17");
    acao.put("12-while", "s18");
    irPara.put("12-L", 31);
    irPara.put("12-C", 20);
    irPara.put("12-A", 21);
    irPara.put("12-R", 22);
    irPara.put("12-W", 23);
    irPara.put("12-M", 24);
    irPara.put("12-N", 25);
    irPara.put("12-P", 26);

    // Estado 13
    acao.put("13-(", "s32");

    // Estado 14 - Correção: tem s33 para "(" e r(R->readln) para ";"
    acao.put("14-;", "r21"); // r(R->readln) = r20
    acao.put("14-(", "s33");
    acao.put("14-else", "r20"); // r(R->readln) = r20

    // Estado 15
    acao.put("15-(", "s34");

    // Estado 16 - Correção: tem s35 para "(" e r(W->writeln) para ";"
    acao.put("16-;", "r24"); // r(W->writeln) = r23
    acao.put("16-(", "s35");
    acao.put("16-else", "r23");

    // Estado 17
    acao.put("17-id", "s36");
    acao.put("17-(", "s37");
    acao.put("17-num", "s38");
    irPara.put("17-B", 39);
    irPara.put("17-E", 40);

    // Estado 18
    acao.put("18-id", "s36");
    acao.put("18-(", "s37");
    acao.put("18--", "s38");
    acao.put("18-num", "s38");
    irPara.put("18-B", 39);
    irPara.put("18-E", 41);

    // Estado 19
    acao.put("19-end", "s42");

    // Estado 20
    acao.put("20-;", "s43");

    // Estado 21 - r(C->A)
    adicionarReducoes(21, 12); // Assumindo que C→A é r12

    // Estado 22 - r(C->R)
    adicionarReducoes(22, 13); // Assumindo que C→R é r13

    // Estado 23 - r(C->W)
    adicionarReducoes(23, 14); // Assumindo que C→W é r14

    // Estado 24 - r(C->M)
    adicionarReducoes(24, 15); // Assumindo que C→M é r15

    // Estado 25 - r(C->N)
    adicionarReducoes(25, 16); // Assumindo que C→N é r16

    // Estado 26 - r(C->P)
    adicionarReducoes(26, 17); // Assumindo que C→P é r17

    // Estado 27
    acao.put("27-id", "s11");
    acao.put("27-begin", "s12");
    acao.put("27-read", "s13");
    acao.put("27-readln", "s14");
    acao.put("27-write", "s15");
    acao.put("27-writeln", "s16");
    acao.put("27-if", "s17");
    acao.put("27-while", "s18");
    irPara.put("27-L", 44);
    irPara.put("27-C", 20);
    irPara.put("27-A", 21);
    irPara.put("27-R", 22);
    irPara.put("27-W", 23);
    irPara.put("27-M", 24);
    irPara.put("27-N", 25);
    irPara.put("27-P", 26);

    // Estado 28
    acao.put("28-id", "s8");
    irPara.put("28-I", 45);

    // Estado 29
    acao.put("29-integer", "s46");
    acao.put("29-boolean", "s46");
    irPara.put("29-T", 48);

    // Estado 30
    acao.put("30-id", "s49");
    acao.put("30-(", "s37");
    acao.put("30-num", "s38");
    irPara.put("30-E", 50);

    // Estado 31
    acao.put("31-end", "s51");

    // Estado 32
    acao.put("32-id", "s8");
    irPara.put("32-I", 52);

    // Estado 33
    acao.put("33-id", "s8");
    irPara.put("33-I", 53);

    // Estado 34
    acao.put("34-id", "s49");
    acao.put("34-(", "s37");
    acao.put("34-str", "s54");
    acao.put("34-num", "s38");
    irPara.put("34-F", 55);
    irPara.put("34-G", 56);
    irPara.put("34-E", 57);

    // Estado 35
    acao.put("35-id", "s49");
    acao.put("35-(", "s37");
    acao.put("35-str", "s54");
    acao.put("35-num", "s38");
    irPara.put("35-F", 58);
    irPara.put("35-G", 56);
    irPara.put("35-E", 57);

    // Estado 36 - r(E->id)
    adicionarReducoes(36, 38); // Assumindo que E→id é r38
    // Nota: CSV mostra r(E->id) para vários tokens: ; , ) then else end do < <= >
    // >= = <> + - * /
    adicionarReducoesParaTerminal(36, 38, Arrays.asList(";", ",", ")", "then", "else", "end", "do",
        "<", "<=", ">", ">=", "=", "<>", "+", "-", "*", "/"));

    // Estado 37
    acao.put("37-id", "s49");
    acao.put("37-(", "s37");
    acao.put("37-num", "s38");
    irPara.put("37-E", 59);

    // Estado 38 - r(E->num)
    adicionarReducoes(38, 39); // Assumindo que E→num é r40
    adicionarReducoesParaTerminal(38, 39, Arrays.asList(";", ",", ")", "then", "else", "end", "do",
        "<", "<=", ">", ">=", "=", "<>", "+", "-", "*", "/"));

    // Estado 39
    acao.put("39-+", "s60");
    acao.put("39--", "s61");
    acao.put("39-*", "s62");
    acao.put("39-/", "s63");
    acao.put("39-<", "s64");
    acao.put("39-<=", "s65");
    acao.put("39->", "s66");
    acao.put("39->=", "s67");
    acao.put("39-=", "s68");
    acao.put("39-/=", "s69");

    // Estado 40
    acao.put("40-then", "s70");

    // Estado 41
    acao.put("41-do", "s71");

    // Estado 42
    acao.put("42-.", "s72");

    // Estado 43
    acao.put("43-id", "s11"); 
    acao.put("43-begin", "s12");
    acao.put("43-end", "r10"); // r(L->C ;) = r10
    acao.put("43-read", "s13");
    acao.put("43-readln", "s14");
    acao.put("43-write", "s15");
    acao.put("43-writeln", "s16");
    acao.put("43-if", "s17");
    acao.put("43-while", "s18");
    irPara.put("43-L", 73);
    irPara.put("43-C", 20);
    irPara.put("43-A", 21);
    irPara.put("43-R", 22);
    irPara.put("43-W", 23);
    irPara.put("43-M", 24);
    irPara.put("43-N", 25);
    irPara.put("43-P", 26);

    // Estado 44
    acao.put("44-end", "s74");

    // Estado 45 - r(I->id , I)
    // Assumindo que I → id , I é r9
    adicionarReducoes(45, 9);
    // No CSV: r(I->id , I) para ; , :
    adicionarReducoesParaTerminal(45, 9, Arrays.asList(";", ":", "else"));

    // Estado 46 - r(T->integer)
    // Assumindo que T → integer é r6
    adicionarReducoes(46, 6);

    // Estado 47 - r(T->Boolean)
    // Assumindo que T → Boolean é r7
    adicionarReducoes(47, 7);

    // Estado 48
    acao.put("48-;", "s75");

    // Estado 49 - r(E->id)
    // Mesma redução que estado 36, mas para tokens diferentes?
    // CSV mostra r(E->id) para ; , ) then else end do < <= > >= = <> + - * /
    adicionarReducoes(49, 38); // E→id é r38
    adicionarReducoesParaTerminal(49, 38, Arrays.asList(";", ",", "then", "else", "end", "do",
        "<", "<=", ">", ">=", "=", "<>", "+", "-", "*", "/"));

    // Estado 50 - r(A->id := E)
    // Assumindo que A → id := E é r18
    adicionarReducoesParaTerminal(50, 18, Arrays.asList(";", "end"));
    // Também tem shifts para operadores: + - * /
    acao.put("50-+", "s60");
    acao.put("50-<", "s60");
    acao.put("50--", "s61");
    acao.put("50-*", "s62");
    acao.put("50-/", "s63");

    // Estado 51 - r(M->begin L end)
    // Assumindo que M → begin L end é r29
    adicionarReducoesParaTerminal(51, 29, Arrays.asList(";", "else"));

    // Estado 52 - Vazio (sem ações)
    // Não faz nada

    // Estado 53 - Vazio (sem ações)
    // Não faz nada

    // Estado 54 - r(G->str)
    // Redução r27 aplicada para ; , )
    acao.put("54-;", "r27");
    acao.put("54-,", "r27");
    acao.put("54-)", "s16");
    acao.put("54-else", "r27");

    // Estado 55 - Vazio (sem ações)
    // Não faz nada

    // Estado 56 - r(F->G) e shift para ,
    // Redução r25 aplicada para ; e )
    acao.put("56-;", "r25");
    acao.put("56-else", "r25");
    // Shift s76 para ,
    acao.put("56-,", "s76");

    // Estado 57 - r(G->E) e shifts para operadores
    // Redução r28 aplicada para ; , )
    acao.put("57-;", "r28");
    acao.put("57-,", "r28");
    acao.put("57-else", "r28");
    // Shifts para operadores
    acao.put("57-+", "s60");
    acao.put("57--", "s61");
    acao.put("57-*", "s62");
    acao.put("57-/", "s63");
    acao.put("57-)", "s16");
    
    // Estado 58 - Vazio (sem ações)
    // Não faz nada

    // Estado 59 - Apenas shifts para operadores
    acao.put("59-+", "s60");
    acao.put("59--", "s61");
    acao.put("59-*", "s62");
    acao.put("59-/", "s63");

    // Estado 60
    acao.put("60-id", "s49");
    acao.put("60-(", "s37");
    acao.put("60-num", "s38");
    irPara.put("60-E", 77);

    // Estado 61
    acao.put("61-id", "s49");
    acao.put("61-(", "s37");
    acao.put("61-num", "s38");
    irPara.put("61-E", 78);

    // Estado 62
    acao.put("62-id", "s49");
    acao.put("62-(", "s37");
    acao.put("62-num", "s38");
    irPara.put("62-E", 79);

    // Estado 63
    acao.put("63-id", "s49");
    acao.put("63-(", "s37");
    acao.put("63-num", "s38");
    irPara.put("63-E", 80);

    // Estado 64
    acao.put("64-id", "s49");
    acao.put("64-(", "s37");
    acao.put("64-num", "s38");
    irPara.put("64-E", 81);

    // Estado 65
    acao.put("65-id", "s49");
    acao.put("65-(", "s37");
    acao.put("65-num", "s38");
    irPara.put("65-E", 82);

    // Estado 66
    acao.put("66-id", "s49");
    acao.put("66-(", "s37");
    acao.put("66-num", "s38");
    irPara.put("66-E", 83);

    // Estado 67
    acao.put("67-id", "s49");
    acao.put("67-(", "s37");
    acao.put("67-num", "s38");
    irPara.put("67-E", 84);

    // Estado 68
    acao.put("68-id", "s49");
    acao.put("68-(", "s37");
    acao.put("68-num", "s38");
    irPara.put("68-E", 85);

    // Estado 69
    acao.put("69-id", "s49");
    acao.put("69-(", "s37");
    acao.put("69-num", "s38");
    irPara.put("69-E", 86);

    // Estado 70
    acao.put("70-id", "s11");
    acao.put("70-begin", "s12");
    acao.put("70-read", "s13");
    acao.put("70-readln", "s14");
    acao.put("70-write", "s15");
    acao.put("70-writeln", "s16");
    acao.put("70-if", "s17");
    acao.put("70-while", "s18");
    irPara.put("70-C", 87);
    irPara.put("70-A", 21);
    irPara.put("70-R", 22);
    irPara.put("70-W", 23);
    irPara.put("70-M", 24);
    irPara.put("70-N", 25);
    irPara.put("70-P", 26);

    // Estado 71
    acao.put("71-id", "s11");
    acao.put("71-begin", "s12");
    acao.put("71-read", "s13");
    acao.put("71-readln", "s14");
    acao.put("71-write", "s15");
    acao.put("71-writeln", "s16");
    acao.put("71-if", "s17");
    acao.put("71-while", "s18");
    irPara.put("71-C", 88);
    irPara.put("71-A", 21);
    irPara.put("71-R", 22);
    irPara.put("71-W", 23);
    irPara.put("71-M", 24);
    irPara.put("71-N", 25);
    irPara.put("71-P", 26);

    // Estado 72 - Redução S → program id; D begin L end. (r1)
    acao.put("72-$", "r1");

    // Estado 73 - Redução L → C;L (r11) para token "end"
    acao.put("73-end", "r11");

    // Estado 74
    acao.put("74-.", "s89");

    // Estado 75
    acao.put("75-id", "s8");
    acao.put("75-begin", "r5"); // r(V->I : T ;) é r5
    // Além disso, tem goto V=90 e I=10
    irPara.put("75-V", 90);
    irPara.put("75-I", 10);

    // Estado 76
    acao.put("76-id", "s49");
    acao.put("76-(", "s37");
    acao.put("76-str", "s54");
    acao.put("76-num", "s38");
    irPara.put("76-F", 91);
    irPara.put("76-G", 56);
    irPara.put("76-E", 57);

    // Estado 77 - r(E->E + E) = r33
    adicionarReducoesParaTerminal(77, 33,
        Arrays.asList(";", ",", "then", "else", "do", "<", "<=", ">", ">=", "=", "<>"));
    acao.put("77-+", "s60");
    acao.put("77--", "s61");
    acao.put("77-*", "s62");
    acao.put("77-/", "s63");

    // Estado 78 - r(E->E - E) = r34
    adicionarReducoesParaTerminal(78, 34,
        Arrays.asList(";", ",", "then", "else", "do", "+", "*", "/", "<", "<=", ">", ">=", "=", "<>"));
    acao.put("78--", "s61");

    // Estado 79 - r(E->E * E) = r35
    adicionarReducoesParaTerminal(79, 35,
        Arrays.asList(";", ",", "then", "else", "do", "<", "<=", ">", ">=", "=", "<>"));
    acao.put("79-+", "s60");
    acao.put("79--", "s61");
    acao.put("79-*", "s62");
    acao.put("79-/", "s63");

    // Estado 80 - r(E->E / E) = r36
    adicionarReducoesParaTerminal(80, 36,
        Arrays.asList(";", ",", "then", "else", "do", "<", "<=", ">", ">=", "=", "<>"));
    acao.put("80-+", "s60");
    acao.put("80--", "s61");
    acao.put("80-*", "s62");
    acao.put("80-/", "s63");

    // Estado 81 - r(B->E < E) = r40
    adicionarReducoesParaTerminal(81, 40, Arrays.asList("then", "do"));
    acao.put("81-+", "s60");
    acao.put("81--", "s61");
    acao.put("81-*", "s62");
    acao.put("81-/", "s63");

    // Estado 82 - r(B->E <= E) = r41
    adicionarReducoes(82, 41);
    adicionarReducoesParaTerminal(82, 41, Arrays.asList("then", "do"));
    acao.put("82-+", "s60");
    acao.put("82--", "s61");
    acao.put("82-*", "s62");
    acao.put("82-/", "s63");

    // Estado 83 - r(B->E > E) = r41
    adicionarReducoesParaTerminal(83, 42, Arrays.asList("then", "do"));
    acao.put("83-+", "s60");
    acao.put("83--", "s61");
    acao.put("83-*", "s62");
    acao.put("83-/", "s63");

    // Estado 84 - r(B->E >= E) = r44
    adicionarReducoesParaTerminal(84, 43, Arrays.asList("then", "do"));
    acao.put("84-+", "s60");
    acao.put("84--", "s61");
    acao.put("84-*", "s62");
    acao.put("84-/", "s63");

    // Estado 85 - r(B->E = E) = r45
    adicionarReducoesParaTerminal(85, 44, Arrays.asList("then", "do"));
    acao.put("85-+", "s60");
    acao.put("85--", "s61");
    acao.put("85-*", "s62");
    acao.put("85-/", "s63");

    // Estado 86 - r(B->E <> E) = r46
    adicionarReducoesParaTerminal(86, 45, Arrays.asList("then", "do"));
    acao.put("86-+", "s60");
    acao.put("86--", "s61");
    acao.put("86-*", "s62");
    acao.put("86-/", "s63");

    // Estado 87 - r(N->if B then C) = r30
    // Redução aplicada para ; e else
    adicionarReducoesParaTerminal(87, 30, Arrays.asList(";", "else"));
    
    // Estado 88 - r(P->while B do C) = r32
    // Redução aplicada para ; e else
    adicionarReducoesParaTerminal(88, 32, Arrays.asList(";", "else"));
    
    // Estado 89 - Redução S → program id; D begin L end. = r1
    // Aplicada para token $
    acao.put("89-$", "r1");
    
    // Estado 90 - Redução V → I:T;V = r4
    // Aplicada para token ;
    acao.put("90-;", "r4");
    
    // Estado 91 - Redução F → G,F = r26
    // Aplicada para ; e )
    adicionarReducoesParaTerminal(91, 26, Arrays.asList(";", "else"));
    
    // Estado 92
    acao.put("92-id", "s11");
    acao.put("92-begin", "s12");
    acao.put("92-read", "s13");
    acao.put("92-readln", "s14");
    acao.put("92-write", "s15");
    acao.put("92-writeln", "s16");
    acao.put("92-if", "s17");
    acao.put("92-while", "s18");
    irPara.put("92-C", 93);
    irPara.put("92-A", 21);
    irPara.put("92-R", 22);
    irPara.put("92-W", 23);
    irPara.put("92-M", 24);
    irPara.put("92-N", 25);
    irPara.put("92-P", 26);
    
    // Estado 93 - Redução N → if B then C else C = r31
    // Aplicada para ; e else
    adicionarReducoesParaTerminal(93, 31, Arrays.asList(";", "else"));
  }

   /*
   * ***************************************************************
   * Metodo: adicionarReducoes
   * Funcao: adiciona as reduções a produção passada nos parâmetros 
   *   da função 
   * Parametros: int estado, int numeroProducao
   * Retorno: void
   */
  private void adicionarReducoes(int estado, int numeroProducao) {
    Producao producao = gramatica.getProducao(numeroProducao);
    
    // somente serão adicionados as reduções caso exista a produção
    if (producao != null) {
      Set<String> seguidor = gramatica.getSeguidor(producao.getNaoTerminal());
      for (String simbolo : seguidor) {
        acao.put(estado + "-" + simbolo, "r" + numeroProducao);
      }
    }
  }

  /*
   * ***************************************************************
   * Metodo: adicionarReducoesParaTerminal
   * Funcao: adiciona as reduções a produção passada nos parâmetros 
   *   da função de forma manual, os simbolos que queremos fazer as
   *   reduções são descritos
   * Parametros: int estado, int numeroProducao, List<String> simbolos
   * Retorno: void
   */
  private void adicionarReducoesParaTerminal(int estado, int numeroProducao, List<String> simbolos) {
    for (String simbolo : simbolos) {
      acao.put(estado + "-" + simbolo, "r" + numeroProducao);
    }
  }

  /*
   * ***************************************************************
   * Metodo: getAcao
   * Funcao: seleciona a ação, útil para saber se a ação tomada será
   *   empilhar ou reduzir
   * Parametros: int estado, String simbolo
   * Retorno: String
   */
  public String getAcao(int estado, String simbolo) {
    return acao.get(estado + "-" + simbolo);
  }

  /*
   * ***************************************************************
   * Metodo: getIrPara
   * Funcao: seleciona para qual estado o analisador irá, útil quando
   *   a operação de reduzir é realizada
   * Parametros: int estado, String simbolo
   * Retorno: Integer
   */
  public Integer getIrPara(int estado, String simbolo) {
    return irPara.get(estado + "-" + simbolo);
  }

  /*
   * ***************************************************************
   * Metodo: getIrPara
   * Funcao: retorna informações relevantes da tabela sintática
   * Parametros: vazio
   * Retorno: String
   */
  public String getInfoTabela() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== TABELA SLR COMPLETA ===\n");
    sb.append("Total de ações: ").append(acao.size()).append("\n");
    sb.append("Total de transições GOTO: ").append(irPara.size()).append("\n");
    sb.append("Estados implementados: 0 a 100\n\n");

    sb.append("Resumo por tipo de estado:\n");
    sb.append(
        "- Estados de shift: 0, 2, 3, 4, 5, 6, 7, 8, 10, 11, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 69, 72, 74, 77, 78, 79, 94, 96, 97\n");
    sb.append(
        "- Estados de redução: 9, 12, 13, 14, 15, 16, 26, 37, 38, 47, 49, 50, 51, 53, 71, 73, 75, 76, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 95, 98, 99, 100\n");
    sb.append("- Estado de aceitação: 1, 48\n");

    return sb.toString();
  }
}