import numpy as np 

def nodominados():

	print("llego")
	
	try:
		ventx, venty, cliplimit, entropia, ssimr, ssimg, ssimb = np.loadtxt('calhouse_230.csv', delimiter=',', unpack=True)
		for idxeval, valeval in enumerate(ventx):
			for idx, val in enumerate(ssimr):
				if (idxeval!=idx):
					if 	ssimr[idxeval] >= ssimr[idx] and ssimg[idxeval] >= ssimg[idx] and ssimb[idxeval] >= ssimb[idx] and entropia[idxeval] >= entropia[idx]:
							print("es dominado")

	except Exception:
		print("error")

nodominados()