import numpy as np 

def main():


	
	# try:
		
	ventx, venty, cliplimit, entropia, ssimr, ssimg, ssimb = np.loadtxt('calhouse_240.csv', delimiter=',', unpack=True,dtype='float')
	i=0
	j=0
	for eachEntropia in entropia:
		j=0
		Dominado='N'

		for eachEntropia2 in entropia:

			if (i!=j and entropia[i]>=entropia[j] and ssimr[i]>=ssimr[j] and ssimg[i]>=ssimg[j] and ssimb[i]>=ssimb[j]):
				print("dominado")
				Dominado='S'
				break
			j+=1
			print(j)
		saveLine= str(ventx[i])+','+str(venty[i])+','+str(cliplimit[i])+','+str(entropia[i])+','+str(ssimr[i])+','+str(ssimg[i])+','+str(ssimb[i])+','+Dominado+'\n'
		saveFile=open('calhouse_240_.csv','a')
		saveFile.write(saveLine)
		saveFile.close()
		i+=1
	# except Exception:
	# 	print("error")