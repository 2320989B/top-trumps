#!/bin/bash

rm coverage_matrix.pdf
soffice --convert-to pdf coverage_matrix.ods --headless

latex customer_spec.tex
pdflatex customer_spec.tex

rm *.aux *.bbl *.blg *.div *.log *.out
