#! /bin/bash

rm *.aux *.bbl *.blg *.dvi *.log *.out *.toc *.lof
pdflatex main
pdflatex main
rm *.aux *.bbl *.blg *.div *.log *.out *.toc *.lof

mv main.pdf Team19Report.pdf
