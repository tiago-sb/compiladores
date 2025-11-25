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
    
  private void inicializarTabelaCompleta() {
    // Estado 0
    acao.put("0-program", "s2");
    irPara.put("0-S", 1);
    
    // Estado 1
    acao.put("1-$", "acc");
    
    // Estado 2
    acao.put("2-id", "s3");
    
    // Estado 3
    acao.put("3-;", "s4");
    
    // Estado 4
    acao.put("4-var", "s7");
    acao.put("4-begin", "s6");
    irPara.put("4-D", 5);
    
    // Estado 5
    acao.put("5-begin", "s8");
    
    // Estado 6
    acao.put("6-id", "s18");
    acao.put("6-begin", "s17");
    acao.put("6-read", "s20");
    acao.put("6-readln", "s21");
    acao.put("6-write", "s23");
    acao.put("6-writeln", "s24");
    acao.put("6-if", "s19");
    acao.put("6-while", "s22");
    irPara.put("6-L", 11);
    irPara.put("6-C", 10);
    irPara.put("6-A", 9);
    irPara.put("6-R", 15);
    irPara.put("6-W", 16);
    irPara.put("6-M", 12);
    irPara.put("6-N", 13);
    irPara.put("6-P", 14);
    
    // Estado 7
    acao.put("7-id", "s27");
    irPara.put("7-V", 26);
    irPara.put("7-I", 25);
    
    // Estado 8
    acao.put("8-id", "s18");
    acao.put("8-begin", "s17");
    acao.put("8-read", "s20");
    acao.put("8-readln", "s21");
    acao.put("8-write", "s23");
    acao.put("8-writeln", "s24");
    acao.put("8-if", "s19");
    acao.put("8-while", "s22");
    irPara.put("8-L", 28);
    irPara.put("8-C", 10);
    irPara.put("8-A", 9);
    irPara.put("8-R", 15);
    irPara.put("8-W", 16);
    irPara.put("8-M", 12);
    irPara.put("8-N", 13);
    irPara.put("8-P", 14);
    
    // Estado 9 - Redução C → A (r12)
    adicionarReducoes(9, 12);
    
    // Estado 10
    acao.put("10-;", "s29");
    acao.put("10-end", "r10");
    
    // Estado 11
    acao.put("11-end", "s30");
    
    // Estado 12 - Redução C → M (r15)
    adicionarReducoes(12, 15);
    
    // Estado 13 - Redução C → N (r16)
    adicionarReducoes(13, 16);
    
    // Estado 14 - Redução C → P (r17)
    adicionarReducoes(14, 17);
    
    // Estado 15 - Redução C → R (r13)
    adicionarReducoes(15, 13);
    
    // Estado 16 - Redução C → W (r14)
    adicionarReducoes(16, 14);
    
    // Estado 17
    acao.put("17-id", "s18");
    acao.put("17-begin", "s17");
    acao.put("17-read", "s20");
    acao.put("17-readln", "s21");
    acao.put("17-write", "s23");
    acao.put("17-writeln", "s24");
    acao.put("17-if", "s19");
    acao.put("17-while", "s22");
    irPara.put("17-L", 31);
    irPara.put("17-C", 10);
    irPara.put("17-A", 9);
    irPara.put("17-R", 15);
    irPara.put("17-W", 16);
    irPara.put("17-M", 12);
    irPara.put("17-N", 13);
    irPara.put("17-P", 14);
    
    // Estado 18
    acao.put("18-:=", "s32");
    
    // Estado 19
    acao.put("19-id", "s37");
    acao.put("19-(", "s33");
    acao.put("19--", "s34");
    acao.put("19-num", "s38");
    irPara.put("19-B", 35);
    irPara.put("19-E", 36);
    
    // Estado 20
    acao.put("20-(", "s39");
    
    // Estado 21
    acao.put("21-(", "s40");
    
    // Estado 22
    acao.put("22-id", "s37");
    acao.put("22-(", "s33");
    acao.put("22--", "s34");
    acao.put("22-num", "s38");
    irPara.put("22-B", 41);
    irPara.put("22-E", 36);
    
    // Estado 23
    acao.put("23-(", "s42");
    
    // Estado 24
    acao.put("24-(", "s43");
    
    // Estado 25
    acao.put("25-:", "s44");
    
    // Estado 26 - Redução D → var V (r3)
    adicionarReducoes(26, 3);
    
    // Estado 27
    acao.put("27-,", "s45");
    acao.put("27-:", "s44");
    
    // Estado 28
    acao.put("28-end", "s46");
    
    // Estado 29
    acao.put("29-id", "s18");
    acao.put("29-begin", "s17");
    acao.put("29-read", "s20");
    acao.put("29-readln", "s21");
    acao.put("29-write", "s23");
    acao.put("29-writeln", "s24");
    acao.put("29-if", "s19");
    acao.put("29-while", "s22");
    irPara.put("29-L", 47);
    irPara.put("29-C", 10);
    irPara.put("29-A", 9);
    irPara.put("29-R", 15);
    irPara.put("29-W", 16);
    irPara.put("29-M", 12);
    irPara.put("29-N", 13);
    irPara.put("29-P", 14);
    
    // Estado 30
    acao.put("30-.", "s48");
    
    // Estado 31
    acao.put("31-;", "r29");
    acao.put("31-end", "s49");
    acao.put("31-else", "r29");
    
    // Estado 32
    acao.put("32-id", "s51");
    acao.put("32-(", "s33");
    acao.put("32--", "s34");
    acao.put("32-num", "s38");
    irPara.put("32-E", 50);
    
    // Estado 33
    acao.put("33-id", "s51");
    acao.put("33-(", "s33");
    acao.put("33--", "s34");
    acao.put("33-num", "s38");
    irPara.put("33-E", 52);
    
    // Estado 34
    acao.put("34-id", "s51");
    acao.put("34-(", "s33");
    acao.put("34--", "s34");
    acao.put("34-num", "s38");
    irPara.put("34-E", 53);
    
    // Estado 35
    acao.put("35-then", "s54");
    
    // Estado 36
    acao.put("36-+", "s56");
    acao.put("36--", "s57");
    acao.put("36-*", "s55");
    acao.put("36-/", "s58");
    acao.put("36-<", "s59");
    acao.put("36-<=", "s60");
    acao.put("36->", "s63");
    acao.put("36->=", "s64");
    acao.put("36-=", "s62");
    acao.put("36-<>", "s61");
    acao.put("36-;", "r39");
    acao.put("36-else", "r39");
    acao.put("36-end", "r39");
    acao.put("36-then", "r39");
    acao.put("36-do", "r39");
    acao.put("36-,", "r39");
    acao.put("36-)", "r39");
    
    // Estado 37 - Redução E → id (r39) e B → id (r47)
    adicionarReducoes(37, 39);
    adicionarReducoes(37, 47);
    
    // Estado 38 - Redução E → num (r40)
    adicionarReducoes(38, 40);
    
    // Estado 39
    acao.put("39-id", "s27");
    irPara.put("39-I", 65);
    
    // Estado 40
    acao.put("40-id", "s27");
    irPara.put("40-I", 66);
    
    // Estado 41
    acao.put("41-do", "s67");
    
    // Estado 42
    acao.put("42-id", "s51");
    acao.put("42-(", "s33");
    acao.put("42--", "s34");
    acao.put("42-str", "s71");
    acao.put("42-num", "s38");
    irPara.put("42-F", 69);
    irPara.put("42-G", 70);
    irPara.put("42-E", 68);
    
    // Estado 43
    acao.put("43-id", "s51");
    acao.put("43-(", "s33");
    acao.put("43--", "s34");
    acao.put("43-str", "s71");
    acao.put("43-num", "s38");
    irPara.put("43-F", 72);
    irPara.put("43-G", 70);
    irPara.put("43-E", 68);
    
    // Estado 44
    acao.put("44-integer", "s75");
    acao.put("44-Boolean", "s73");
    irPara.put("44-T", 74);
    
    // Estado 45
    acao.put("45-id", "s27");
    irPara.put("45-I", 76);
    
    // Estado 46
    acao.put("46-.", "s77");
    
    // Estado 47 - Redução L → C;L (r11)
    adicionarReducoes(47, 11);
    
    // Estado 48 - Aceitação
    acao.put("48-$", "acc");
    
    // Estado 49 - Redução M → begin L end (r29)
    adicionarReducoes(49, 29);
    
    // Estado 50
    acao.put("50-;", "r18");
    acao.put("50-else", "r18");
    acao.put("50-end", "r18");
    acao.put("50-+", "s56");
    acao.put("50--", "s57");
    acao.put("50-*", "s55");
    acao.put("50-/", "s58");
    
    // Estado 51 - Redução E → id (r39)
    adicionarReducoes(51, 39);
    
    // Estado 52
    acao.put("52-+", "s56");
    acao.put("52--", "s57");
    acao.put("52-*", "s55");
    acao.put("52-/", "s58");
    acao.put("52-)", "s78");
    
    // Estado 53
    acao.put("53-+", "s56");
    acao.put("53--", "s57");
    acao.put("53-*", "s55");
    acao.put("53-/", "s58");
    
    // Estado 54
    acao.put("54-id", "s18");
    acao.put("54-begin", "s17");
    acao.put("54-read", "s20");
    acao.put("54-readln", "s21");
    acao.put("54-write", "s23");
    acao.put("54-writeln", "s24");
    acao.put("54-if", "s19");
    acao.put("54-while", "s22");
    irPara.put("54-C", 79);
    irPara.put("54-A", 9);
    irPara.put("54-R", 15);
    irPara.put("54-W", 16);
    irPara.put("54-M", 12);
    irPara.put("54-N", 13);
    irPara.put("54-P", 14);
    
    // Estado 55
    acao.put("55-id", "s51");
    acao.put("55-(", "s33");
    acao.put("55--", "s34");
    acao.put("55-num", "s38");
    irPara.put("55-E", 80);
    
    // Estado 56
    acao.put("56-id", "s51");
    acao.put("56-(", "s33");
    acao.put("56--", "s34");
    acao.put("56-num", "s38");
    irPara.put("56-E", 81);
    
    // Estado 57
    acao.put("57-id", "s51");
    acao.put("57-(", "s33");
    acao.put("57--", "s34");
    acao.put("57-num", "s38");
    irPara.put("57-E", 82);
    
    // Estado 58
    acao.put("58-id", "s51");
    acao.put("58-(", "s33");
    acao.put("58--", "s34");
    acao.put("58-num", "s38");
    irPara.put("58-E", 83);
    
    // Estado 59
    acao.put("59-id", "s51");
    acao.put("59-(", "s33");
    acao.put("59--", "s34");
    acao.put("59-num", "s38");
    irPara.put("59-E", 84);
    
    // Estado 60
    acao.put("60-id", "s51");
    acao.put("60-(", "s33");
    acao.put("60--", "s34");
    acao.put("60-num", "s38");
    irPara.put("60-E", 85);
    
    // Estado 61
    acao.put("61-id", "s51");
    acao.put("61-(", "s33");
    acao.put("61--", "s34");
    acao.put("61-num", "s38");
    irPara.put("61-E", 86);
    
    // Estado 62
    acao.put("62-id", "s51");
    acao.put("62-(", "s33");
    acao.put("62--", "s34");
    acao.put("62-num", "s38");
    irPara.put("62-E", 87);
    
    // Estado 63
    acao.put("63-id", "s51");
    acao.put("63-(", "s33");
    acao.put("63--", "s34");
    acao.put("63-num", "s38");
    irPara.put("63-E", 88);
    
    // Estado 64
    acao.put("64-id", "s51");
    acao.put("64-(", "s33");
    acao.put("64--", "s34");
    acao.put("64-num", "s38");
    irPara.put("64-E", 89);
    
    // Estado 65
    acao.put("65-)", "s90");
    
    // Estado 66
    acao.put("66-)", "s91");
    
    // Estado 67
    acao.put("67-id", "s18");
    acao.put("67-begin", "s17");
    acao.put("67-read", "s20");
    acao.put("67-readln", "s21");
    acao.put("67-write", "s23");
    acao.put("67-writeln", "s24");
    acao.put("67-if", "s19");
    acao.put("67-while", "s22");
    irPara.put("67-C", 92);
    irPara.put("67-A", 9);
    irPara.put("67-R", 15);
    irPara.put("67-W", 16);
    irPara.put("67-M", 12);
    irPara.put("67-N", 13);
    irPara.put("67-P", 14);
    
    // Estado 68
    acao.put("68-+", "s56");
    acao.put("68--", "s57");
    acao.put("68-*", "s55");
    acao.put("68-/", "s58");
    acao.put("68-,", "r28");
    acao.put("68-)", "r28");
    
    // Estado 69
    acao.put("69-,", "s94");
    acao.put("69-)", "s93");
    
    // Estado 70
    acao.put("70-,", "r25");
    acao.put("70-)", "r25");
    
    // Estado 71 - Redução G → str (r27)
    adicionarReducoes(71, 27);
    
    // Estado 72
    acao.put("72-,", "s94");
    acao.put("72-)", "s95");
    
    // Estado 73 - Redução T → Boolean (r7)
    adicionarReducoes(73, 7);
    
    // Estado 74
    acao.put("74-;", "s96");
    
    // Estado 75 - Redução T → integer (r6)
    adicionarReducoes(75, 6);
    
    // Estado 76 - Redução I → id,I (r9)
    adicionarReducoes(76, 9);
    
    // Estado 77 - Redução S → program id; D begin L end. (r1)
    acao.put("77-$", "r1");
    
    // Estado 78 - Redução E → (E) (r38)
    adicionarReducoes(78, 38);
    
    // Estado 79
    acao.put("79-;", "r30");
    acao.put("79-else", "s97");
    acao.put("79-end", "r30");
    
    // Estado 80
    acao.put("80-+", "s56");
    acao.put("80--", "s57");
    acao.put("80-*", "s55");
    acao.put("80-/", "s58");
    acao.put("80-;", "r35");
    acao.put("80-else", "r35");
    acao.put("80-end", "r35");
    acao.put("80-then", "r35");
    acao.put("80-do", "r35");
    acao.put("80-,", "r35");
    acao.put("80-)", "r35");
    acao.put("80-<", "r35");
    acao.put("80-<=", "r35");
    acao.put("80->", "r35");
    acao.put("80->=", "r35");
    acao.put("80-=", "r35");
    acao.put("80-<>", "r35");
    
    // Estado 81
    acao.put("81-+", "s56");
    acao.put("81--", "s57");
    acao.put("81-*", "s55");
    acao.put("81-/", "s58");
    acao.put("81-;", "r33");
    acao.put("81-else", "r33");
    acao.put("81-end", "r33");
    acao.put("81-then", "r33");
    acao.put("81-do", "r33");
    acao.put("81-,", "r33");
    acao.put("81-)", "r33");
    acao.put("81-<", "r33");
    acao.put("81-<=", "r33");
    acao.put("81->", "r33");
    acao.put("81->=", "r33");
    acao.put("81-=", "r33");
    acao.put("81-<>", "r33");
    
    // Estado 82
    acao.put("82-+", "s56");
    acao.put("82--", "s57");
    acao.put("82-*", "s55");
    acao.put("82-/", "s58");
    acao.put("82-;", "r34");
    acao.put("82-else", "r34");
    acao.put("82-end", "r34");
    acao.put("82-then", "r34");
    acao.put("82-do", "r34");
    acao.put("82-,", "r34");
    acao.put("82-)", "r34");
    acao.put("82-<", "r34");
    acao.put("82-<=", "r34");
    acao.put("82->", "r34");
    acao.put("82->=", "r34");
    acao.put("82-=", "r34");
    acao.put("82-<>", "r34");
    
    // Estado 83
    acao.put("83-+", "s56");
    acao.put("83--", "s57");
    acao.put("83-*", "s55");
    acao.put("83-/", "s58");
    acao.put("83-;", "r36");
    acao.put("83-else", "r36");
    acao.put("83-end", "r36");
    acao.put("83-then", "r36");
    acao.put("83-do", "r36");
    acao.put("83-,", "r36");
    acao.put("83-)", "r36");
    acao.put("83-<", "r36");
    acao.put("83-<=", "r36");
    acao.put("83->", "r36");
    acao.put("83->=", "r36");
    acao.put("83-=", "r36");
    acao.put("83-<>", "r36");
    
    // Estado 84
    acao.put("84-+", "s56");
    acao.put("84--", "s57");
    acao.put("84-*", "s55");
    acao.put("84-/", "s58");
    acao.put("84-;", "r41");
    acao.put("84-else", "r41");
    acao.put("84-end", "r41");
    acao.put("84-then", "r41");
    acao.put("84-do", "r41");
    acao.put("84-,", "r41");
    acao.put("84-)", "r41");
    
    // Estado 85
    acao.put("85-+", "s56");
    acao.put("85--", "s57");
    acao.put("85-*", "s55");
    acao.put("85-/", "s58");
    acao.put("85-;", "r42");
    acao.put("85-else", "r42");
    acao.put("85-end", "r42");
    acao.put("85-then", "r42");
    acao.put("85-do", "r42");
    acao.put("85-,", "r42");
    acao.put("85-)", "r42");
    
    // Estado 86
    acao.put("86-+", "s56");
    acao.put("86--", "s57");
    acao.put("86-*", "s55");
    acao.put("86-/", "s58");
    acao.put("86-;", "r46");
    acao.put("86-else", "r46");
    acao.put("86-end", "r46");
    acao.put("86-then", "r46");
    acao.put("86-do", "r46");
    acao.put("86-,", "r46");
    acao.put("86-)", "r46");
    
    // Estado 87
    acao.put("87-+", "s56");
    acao.put("87--", "s57");
    acao.put("87-*", "s55");
    acao.put("87-/", "s58");
    acao.put("87-;", "r45");
    acao.put("87-else", "r45");
    acao.put("87-end", "r45");
    acao.put("87-then", "r45");
    acao.put("87-do", "r45");
    acao.put("87-,", "r45");
    acao.put("87-)", "r45");
    
    // Estado 88
    acao.put("88-+", "s56");
    acao.put("88--", "s57");
    acao.put("88-*", "s55");
    acao.put("88-/", "s58");
    acao.put("88-;", "r43");
    acao.put("88-else", "r43");
    acao.put("88-end", "r43");
    acao.put("88-then", "r43");
    acao.put("88-do", "r43");
    acao.put("88-,", "r43");
    acao.put("88-)", "r43");
    
    // Estado 89
    acao.put("89-+", "s56");
    acao.put("89--", "s57");
    acao.put("89-*", "s55");
    acao.put("89-/", "s58");
    acao.put("89-;", "r44");
    acao.put("89-else", "r44");
    acao.put("89-end", "r44");
    acao.put("89-then", "r44");
    acao.put("89-do", "r44");
    acao.put("89-,", "r44");
    acao.put("89-)", "r44");
    
    // Estado 90 - Redução R → read (I) (r19)
    adicionarReducoes(90, 19);
    
    // Estado 91 - Redução R → readln (I) (r21)
    adicionarReducoes(91, 21);
    
    // Estado 92 - Redução P → while B do C (r32)
    adicionarReducoes(92, 32);
    
    // Estado 93 - Redução W → write (F) (r22)
    adicionarReducoes(93, 22);
    
    // Estado 94
    acao.put("94-id", "s51");
    acao.put("94-(", "s33");
    acao.put("94--", "s34");
    acao.put("94-str", "s71");
    acao.put("94-num", "s38");
    irPara.put("94-F", 98);
    irPara.put("94-G", 70);
    irPara.put("94-E", 68);
    
    // Estado 95 - Redução W → writeln (F) (r24)
    adicionarReducoes(95, 24);
    
    // Estado 96
    acao.put("96-id", "s27");
    acao.put("96-var", "s7");
    acao.put("96-begin", "s6");
    irPara.put("96-V", 99);
    irPara.put("96-I", 25);
    
    // Estado 97
    acao.put("97-id", "s18");
    acao.put("97-begin", "s17");
    acao.put("97-read", "s20");
    acao.put("97-readln", "s21");
    acao.put("97-write", "s23");
    acao.put("97-writeln", "s24");
    acao.put("97-if", "s19");
    acao.put("97-while", "s22");
    irPara.put("97-C", 100);
    irPara.put("97-A", 9);
    irPara.put("97-R", 15);
    irPara.put("97-W", 16);
    irPara.put("97-M", 12);
    irPara.put("97-N", 13);
    irPara.put("97-P", 14);
    
    // Estado 98 - Redução F → G,F (r26)
    adicionarReducoes(98, 26);
    
    // Estado 99 - Redução V → I:T;V (r4)
    adicionarReducoes(99, 4);
    
    // Estado 100 - Redução N → if B then C else C (r31)
    adicionarReducoes(100, 31);
    
    // Reduções para produções que não aparecem explicitamente na tabela
    // Estado 20 (readln sem parênteses) - r20
    adicionarReducoesParaTerminal(20, 20, Arrays.asList(";", "else", "end"));
    
    // Estado 23 (writeln sem parênteses) - r23
    adicionarReducoesParaTerminal(23, 23, Arrays.asList(";", "else", "end"));
    
    // Estado 27 (I → id) - r8
    adicionarReducoesParaTerminal(27, 8, Arrays.asList(":", ";"));
    
    // Estado 47 (L → C;) - r10
    adicionarReducoesParaTerminal(47, 10, Arrays.asList("end"));
    
    // Estado 79 (N → if B then C) - r30  
    adicionarReducoesParaTerminal(79, 30, Arrays.asList(";", "end"));
  }
    
  private void adicionarReducoes(int estado, int numeroProducao) {
    Producao producao = gramatica.getProducao(numeroProducao);
    if (producao != null) {
      Set<String> seguidor = gramatica.getSeguidor(producao.getNaoTerminal());
      for (String simbolo : seguidor) {
        acao.put(estado + "-" + simbolo, "r" + numeroProducao);
      }
    }
  }
  
  private void adicionarReducoesParaTerminal(int estado, int numeroProducao, List<String> simbolos) {
    for (String simbolo : simbolos) {
      acao.put(estado + "-" + simbolo, "r" + numeroProducao);
    }
  }
    
  public String getAcao(int estado, String simbolo) {
    return acao.get(estado + "-" + simbolo);
  }
  
  public Integer getIrPara(int estado, String simbolo) {
    return irPara.get(estado + "-" + simbolo);
  }
  
  public String getInfoTabela() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== TABELA SLR COMPLETA ===\n");
    sb.append("Total de ações: ").append(acao.size()).append("\n");
    sb.append("Total de transições GOTO: ").append(irPara.size()).append("\n");
    sb.append("Estados implementados: 0 a 100\n\n");
    
    sb.append("Resumo por tipo de estado:\n");
    sb.append("- Estados de shift: 0, 2, 3, 4, 5, 6, 7, 8, 10, 11, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 69, 72, 74, 77, 78, 79, 94, 96, 97\n");
    sb.append("- Estados de redução: 9, 12, 13, 14, 15, 16, 26, 37, 38, 47, 49, 50, 51, 53, 71, 73, 75, 76, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 95, 98, 99, 100\n");
    sb.append("- Estado de aceitação: 1, 48\n");
    
    return sb.toString();
  }
}