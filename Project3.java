
import java.lang.Math;

public class Project3 {

	public static void main(String[] args) {

        /** ~~~~~~~~~ ****FIRST EQUATION**** ~~~~~~~~~~~ */
        System.out.println("\n~~~~\t\t\t****FIRST EQUATION****\t\t\t~~~~");
		
        /** BISECTION METHOD */
        System.out.println("\n~~~~\t\t\tBISECTION METHOD\t\t\t~~~~");

         //a=0, b=1
        System.out.println("\nBounds:\ta=0,\tb=1");
		Bisection(0, 1,"a");
		
		//a=1, b=2
        System.out.println("\nBounds:\ta=1,\tb=2");
		Bisection(1, 2,"a");
		
		//a=3, b=4
        System.out.println("\nBounds:\ta=3,\tb=4");
		Bisection(3, 4,"a");

        /** NEWTON-RAPHSON */
        System.out.println("\n~~~~\t\t\tNEWTON-RAPHSON\t\t\t~~~~");

		System.out.println("\nStarting value = 1");
		NewtonRaphson(1,"a");
		System.out.println("\nStarting value = 2");
		NewtonRaphson(2,"a");
		System.out.println("\nStarting value = 3");
		NewtonRaphson(3,"a");
		
		/** SECANT METHOD */
        System.out.println("\n~~~~\t\t\tSECANT METHOD\t\t\t~~~~");
		//a=0, b=1
		System.out.println("Bounds:\ta=0,\tb=1");
		Secant(0,1,"a");
		//a=1, b=2
		System.out.println("Bounds:\ta=1,\tb=2");
		Secant(1,2,"a");
		//a=3, b=4
        System.out.println("Bounds:\ta=3,\tb=4");
		Secant(3,4,"a");
		
		/** FALSE-POSITION */
        System.out.println("\n~~~~\t\t\tFALSE-POSITION\t\t\t~~~~");
		//a=0, b=1
		System.out.println("\nBounds:\ta=0,\tb=1");
		FalsePosition(0, 1,"a");
		//a=1, b=3
		System.out.println("\nBounds:\ta=1,\tb=3");
		FalsePosition(1, 3,"a");
		//a=3, b=4
		System.out.println("\nBounds:\ta=3,\tb=4");
		FalsePosition(3, 4,"a");

        /** MODIFIED SECANT */
        System.out.println("\n~~~~\t\t\tMODIFIED SECANT\t\t\t~~~~");
		
		System.out.println("Bounds:\ta=0,\tb=1");
		ModifiedSecant(0, 1,"a");
		
		System.out.println("Bounds:\ta=1,\tb=2");
		ModifiedSecant(1, 2,"a");
		
		System.out.println("Bounds:\ta=3,\tb=4");
		ModifiedSecant(3, 4,"a");

        System.out.println("\n~~~~\t\t\t****SECOND EQUATION****\t\t\t~~~~");
		
        /** BISECTION METHOD */
        System.out.println("\n~~~~\t\t\tBISECTION METHOD\t\t\t~~~~");

         //a=121, b=129
        System.out.println("\nBounds:\ta=121,\tb=129");
		Bisection(121, 129,"b");

        /** NEWTON-RAPHSON */
        System.out.println("\n~~~~\t\t\tNEWTON-RAPHSON\t\t\t~~~~");

		System.out.println("\nStarting value = 125");
		NewtonRaphson(125,"b");

        /** SECANT METHOD */
        System.out.println("\n~~~~\t\t\tSECANT METHOD\t\t\t~~~~");
		//a=121, b=129
		System.out.println("Bounds:\ta=121,\tb=129");
		Secant(121,129,"b");

        /** FALSE-POSITION */
        System.out.println("\n~~~~\t\t\tFALSE-POSITION\t\t\t~~~~");
		//a=121, b=129
		System.out.println("\nBounds:\ta=121,\tb=129");
		FalsePosition(121, 129,"b");

        /** MODIFIED SECANT */
        System.out.println("\n~~~~\t\t\tMODIFIED SECANT\t\t\t~~~~");
		//a=121, b=129
		System.out.println("Bounds:\ta=121,\tb=129");
		ModifiedSecant(121, 129,"b");

	}
	static double e = 0.01;
	static int MAX = 100;
	static double error;
	
	static double equations(double x, int which, String eq) {

        if(which==1 && eq.equals("a"))
		return ( 2*Math.pow(x,3) -11.7*Math.pow(x,2) +17.7*x -5 );
        else if(which==2 && eq.equals("a"))
        return ( 6*Math.pow(x,2) -(23.4)*x + 17.7 );
        else if(which==1 && eq.equals("b"))
        return ( x + 10 - x*Math.cosh(50/x) );
        else if(which==2 && eq.equals("b"))
        return ( (50*Math.sinh(50/x)/x) - Math.cosh(50/x)+ 1 );

        return 0;
	}
	
	static void Bisection(double a,double b, String eq) {
        double c = (a+b)/2;
		double last = 0;
        int n=0;
		error = Math.abs(((c-last)/c));

		while ((error >= e) && (n < MAX)) {
			c = (a+b)/2;
			error = Math.abs(((c-last)/c));
            System.out.printf("Iteration: " +n+ "\tc: %.5f\terror: %.5f\n", c, error);
			
			if (equations(c,1,eq) * equations(a,1,eq) > 0)
				a=c;
			else if (equations(c,1,eq) * equations(a,1,eq) < 0)
				b=c;
            else if (equations(c,1,eq) == 0.0) 
				break;
			
			last = c;
			n++;
		}
	}
	
	static void NewtonRaphson(double val, String eq) {
        int n=0;
        double last = 0;
		double div = equations(val,1,eq) / equations(val,2,eq);	
        //Xi+1	
		val -= div;
		error = Math.abs( ((val-last) / val) );
		
		while ((error >= e) && (n < MAX) ) {
			System.out.printf("Iteration: " +n+ "\troot: %.5f\terror: %.5f\n", val, error);
			div = equations(val,1,eq) / equations(val,2,eq);			
			last = val;
			val -= div;			
			error = Math.abs(div);
			n++;			
		}
		System.out.printf("Iteration: " +n+ "\troot: %.5f\terror: %.5f\n", val, error);
	}
	
	static void Secant(double a, double b, String eq) {
        int n=0;
        double last = 0;
		double c = (a*equations(b,1,eq) - b * equations(a,1,eq)) / (equations(b,1,eq) - equations(a,1,eq));
		error = Math.abs(((c - last) /c));

		if (equations(a,1,eq) * equations(b,1,eq) < 0) {
			while ((error >= e) && (n < MAX)) {
				c = (a*equations(b,1,eq) - b*equations(a,1,eq)) / (equations(b,1,eq) - equations(a,1,eq));
				error = Math.abs(((c - last)/c));
                System.out.printf("Iteration: " +n+ "\tc: %.5f\terror: %.5f\n", c, error);
				if (equations(a,1,eq) * equations(c,1,eq) == 0)
					break;

				a = b;
				b = c;
				n++;
				last = c;							
			}
			System.out.println();
		} else
			System.out.println("Root not found.");
	}
	
	static void FalsePosition(double a, double b, String eq) {
        int n =0;
        double last = 0;
		double c = (a * equations(b,1,eq) - b * equations(a,1,eq)) / (equations(b,1,eq) - equations(a,1,eq));
		error = Math.abs( ((c-last) / c) );

		while ( (error >= e) && (n < MAX) ) {
			c = (a * equations(b,1,eq) - b * equations(a,1,eq)) / (equations(b,1,eq) - equations(a,1,eq));
			error = Math.abs( ((c-last)/c) );
            System.out.printf("Iteration: " +n+ "\tc: %.5f\terror: %.5f\n", c, error);

			if (equations(c,1,eq) * equations(b,1,eq) < 0)
				a = c;
			else if (equations(c,1,eq) * equations(a,1,eq) < 0)
				b = c;
            else if (equations(c,1,eq) == 0)
			    break;
			
			last = c;			
			n++;
		}
	}
	
	static void ModifiedSecant(double a, double b, String eq) {
        double delta = 0.01;
        int n = 0;
		double c = b - ((b-a) / (equations(b,1,eq) - equations(a,1,eq))) * equations(b,1,eq);
		error = Math.abs(equations(b,1,eq) - equations(a,1,eq));

		if (equations(a,1,eq) * equations(b,1,eq) < 0) {
			while ((error >= (delta*Math.abs(equations(b,1,eq)))) && (n< MAX)) {
				System.out.printf("Iteration: " +n+ "\tc: %.5f\terror: %.5f\n", c, error);

				c = b - ( (b-a) / (equations(b,1,eq) - equations(a,1,eq)) ) * equations(b,1,eq);
				error = Math.abs(equations(b,1,eq) - equations(a,1,eq));
							
				if (equations(a,1,eq) * equations(c,1,eq) == 0)
					break;	

				a = b;
				b = c;
				n++;						
			}
		} else
			System.out.println("Root not found.");
	}

}
