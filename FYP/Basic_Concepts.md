##Del/Nabla

Del is not a specific operator, but rather a convenient mathematical notation for those three operators, that makes many equations easier to write and remember. The del symbol can be interpreted as a vector of partial derivative operators, and its three possible meanings--gradient, divergence, and curl--can be formally viewed as the product with a scalar, dot product, and cross product, respectively, of the del "operator" with the field.

##Curl

The curl is a vector operator that describes the infinitesimal rotation of a 3-dimensional vector field. $\nabla\times F$

![Curl Formula](https://upload.wikimedia.org/math/c/b/7/cb77a0a122723f78710d07bf0fbd4a0c.png)

_The velocity field is irrotational if the curl of field is zero_ $(\nabla\times F = 0)$

##Gradient

The gradient has the magnitude of the rate of change in the direction of that change:

$\nabla f(\vec{x})=\left\langle\frac{\partial}{\partial x_1}f,\frac{\partial}{\partial x_2}f,\dots,\frac{\partial}{\partial x_n}f\right\rangle$

For example, the gradient of the distance from a given point is a vector field of unit length vectors pointing away from the given point.

##Divergence

Whereas the divergence is the measure of the amount of flow out of a given volume minus the amount of flow into a given volume

![Divergence Formula](https://upload.wikimedia.org/math/9/8/8/98859fd14385686b5b74b644e801da0e.png)

##Incompressible Flow

If density of an volume that moves with the flow velocity does not change during fluid flow then flow is Incompressible. An equivalent statement that implies incompressibility is that the divergence of the flow velocity is zero:

$\nabla . V = 0$

# Laplacian
The $\color{#C00000}{\text{divergence}}$ of the $\color{#00A000}{\text{gradient}}$ is also called the Laplacian. So we convert a scalar field into vector and then vector is converted into a scalar using divergence.

$\color{#C00000}{\nabla\cdot}\color{#00A000}{\nabla}=\color{#0000FF}{\Delta}$

##Laplace Equation

Laplacian of a scalar field equal to zero is called Laplace equation

![Laplace equation Formula](https://upload.wikimedia.org/math/4/b/3/4b390c808a0af0cb3b1771acbec52251.png)

The general theory of solutions to Laplace's equation is known as potential theory. The solutions of Laplace's equation are the harmonic functions.

##Scalar Potential

Scalar potential, simply stated, describes the situation where the difference in the potential energies of an object in two different positions depends only on the positions, not upon the path taken by the object in traveling from one position to the other. It is a scalar field in three-space: a directionless value (scalar) that depends only on its location. A familiar example is potential energy due to gravity.

![Scalar Potential Formula](https://upload.wikimedia.org/math/9/9/3/99303b6a5aa74971a7ef43b4e83387d0.png)

##Velocity Potential

A velocity potential is a scalar potential used in potential flow theory. It is used in continuum mechanics, when a continuum occupies a simply-connected region and is irrotational.

![Velocity Potential Formula](https://upload.wikimedia.org/math/6/8/2/68268281b1d1bddcd2cf40b111664db3.png)

where  $\mathbf{u}$  denotes the flow velocity. As a result,  $\mathbf{u}$  can be represented as the gradient of a scalar function $\Phi$:

![Velocity Potential Formula](https://upload.wikimedia.org/math/d/4/9/d49d08f3009ceb05120a4c8ebbaf1fca.png)

$\Phi$ is known as a velocity potential for $\mathbf{u}$.

##Potential Flow

If a flow is irrotational and non viscous then its Potential flow. Potential flow describes the velocity field as gradient of a scalar function $\phi$.

$v = \nabla \phi$

 Also due to irrotational nature, Curl of velocity field is zero. So we can write:

 $\nabla v = 0$

 Combining two equations gives us Laplace equation which is divergence of gradient of a scalar field is equal to zero.

$\Delta v = 0$
or $\nabla (\nabla \phi) = 0$

So we can find $\phi$ by solving Laplace equation and then find velocity field by taking gradient of $\phi$.

##Continuity Equation

It is conversation of mass in a fixed volume. And states that inlet mass flow rate must be equal to outlet mass flow rate if amount of fluid in fixed volume does not change.

$\Sigma (V_i A_i) = \Sigma (V_o A_o)$

## Zaryab Register
* Conservation of momentum -> Newton 2nd law  = 3 equation
* Conservation of mass = 1 equation

These 4 equation gives us navier strokes equation

* Energy equation
