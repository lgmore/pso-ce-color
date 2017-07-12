import numpy as np 

def main():


	
	# try:
		
	ventx, venty, cliplimit= np.loadtxt('calhouse_240_var.csv', delimiter=',', unpack=True,dtype='float')
	nombreimagen='calhouse_0230.jpg'
	i=0
	for vx in ventx:
		subprocess.call(['./imprimir-imagenes',str(ventx[i],str(venty[i]),str(cliplimit[i]])
		i+=1
	# except Exception:
	# 	print("error")