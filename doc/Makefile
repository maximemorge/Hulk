# A Makefile to easily handle the compilation of
# papers in the ARGUGRID project
#
# NOTE: We assume that you have a directory structure similar to
# the following:
#
#  $(SRC)/
#    figures/
#    $(SRC).tex
#    $(SRC).bib
#    Makefile
#
# So we copy the resulting pdf's to the parent directory.

# LaTeX sourcecode of slides
SRC = hulkDocumentationFr

# submitted 0.5
# how the resulting files should be named
VERSION = $(SRC)-0.1

# create slides and handouts
final:
		mv $(SRC).pdf ../$(VERSION).pdf


# create the normal report
paper:		$(SRC).tex
		pdflatex -shell-escape $(SRC).tex
		bibtex  $(SRC)
		pdflatex -shell-escape $(SRC).tex
		pdflatex -shell-escape $(SRC).tex

# --------------------------------
clean:
	rm -rf *.log *.out *~ *.bak *.aux *.dvi  *.blg *.idx *.bbl
	rm -rf *.cb  *.lof *.los *.lot *.toc *.tpt *.tpm *.cache
	rm -rf _minted-* auto/

# --------------------------------
cleanall:
	${MAKE} clean
	rm -f *.ps *.pdf

