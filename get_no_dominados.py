import numpy as np 
import traceback
def nodominados():

	print("llego")
	
	try:
		formato = '%s %s %s %s %s %s %s %s %s'
		indice, ventx, venty, cliplimit, entropia, ssimr, ssimg, ssimb = np.loadtxt('calhouse_237.csv', delimiter=' ', unpack=True)
		listadominados=np.array([])
		with open('calhouse_237'+'_2.csv', 'ab') as abc:
			for idxeval, valeval in enumerate(ventx):
				esdominado='N'
				for idx, val in enumerate(ssimr):
					if (idxeval!=idx):
						if 	ssimr[idxeval] <= ssimr[idx] and ssimg[idxeval] <= ssimg[idx] and ssimb[idxeval] <= ssimb[idx] and entropia[idxeval] <= entropia[idx]:
								esdominado='S'
								listadominados=np.append(listadominados,esdominado)
								auxiliar=np.column_stack([indice[idxeval], ventx[idxeval],venty[idxeval],cliplimit[idxeval],entropia[idxeval],ssimr[idxeval],ssimg[idxeval],ssimb[idxeval],esdominado]) 
								print(idxeval,idx,auxiliar)
								np.savetxt(abc, auxiliar ,fmt=formato, delimiter=" ")
								break
				if esdominado=='N':
					listadominados=np.append(listadominados,esdominado)
					auxiliar=np.column_stack([indice[idxeval], ventx[idxeval],venty[idxeval],cliplimit[idxeval],entropia[idxeval],ssimr[idxeval],ssimg[idxeval],ssimb[idxeval],esdominado]) 
					np.savetxt(abc,auxiliar ,fmt=formato, delimiter=" ")

		print(listadominados)
	except Exception:
		print(traceback.format_exc())
		print("error")

nodominados()