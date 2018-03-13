package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = "vrai" | "faux"
csteC = \"([^\"]|(\"\"))+\"

type = "entier"
idf = [a-zA-Z][a-zA-Z0-9]*

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaireSlashSlash = [/][/].*

%%

"+"                	{ return symbol(CodesLexicaux.PLUS); }
"-"                	{ return symbol(CodesLexicaux.MOINS); }
"*"                	{ return symbol(CodesLexicaux.MULT); }
"/"                	{ return symbol(CodesLexicaux.DIV); }

"=="                    { return symbol(CodesLexicaux.EGALEGAL); }
"!="                    { return symbol(CodesLexicaux.DIFF); }
"<"                	{ return symbol(CodesLexicaux.INF); }
">"                	{ return symbol(CodesLexicaux.SUP); }

"et"                	{ return symbol(CodesLexicaux.ET); }
"ou"                	{ return symbol(CodesLexicaux.OU); }
"non"                	{ return symbol(CodesLexicaux.NON); }

"("                	{ return symbol(CodesLexicaux.PAROUV); }
")"                	{ return symbol(CodesLexicaux.PARFER); }

"=" { return symbol(CodesLexicaux.EGAL); }

";" { return symbol(CodesLexicaux.POINTVIRGULE); }
"," { return symbol(CodesLexicaux.VIRGULE); }

{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{csteC}      	        { return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }

"programme" { return symbol(CodesLexicaux.SYM_PROG); }
"debut" { return symbol(CodesLexicaux.DEBUT); }
"fin" { return symbol(CodesLexicaux.FIN); }
"fonction" { return symbol(CodesLexicaux.FONCTION); }
"retourne" { return symbol(CodesLexicaux.SYM_RETOURNE); }

"ecrire" { return symbol(CodesLexicaux.SYM_ECRIRE); }
"lire" { return symbol(CodesLexicaux.SYM_LIRE); } 

"si" { return symbol(CodesLexicaux.SI); } 
"alors" { return symbol(CodesLexicaux.ALORS); } 
"sinon" { return symbol(CodesLexicaux.SINON); } 
"fsi" { return symbol(CodesLexicaux.FINSI); } 

"tantque" { return symbol(CodesLexicaux.TANTQUE); } 
"repeter" { return symbol(CodesLexicaux.REPETER); } 
"fintantque" { return symbol(CodesLexicaux.FINTANTQUE); } 

{type} { return symbol(CodesLexicaux.TYPE, yytext()); }
{idf} { return symbol(CodesLexicaux.IDF, yytext()); }

{commentaireSlashSlash} {}
{espace}                {}

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()); }
