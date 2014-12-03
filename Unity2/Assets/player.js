var movespeed : float; 
var turnspeed : float;
var jumpforce : float;
var myBullet : Transform;
var myMuzzle : Transform;

function Update () {
	if(Input.GetButton("Forward")){
		transform.Translate(transform.forward * movespeed * Time.deltaTime);
	}
	if(Input.GetButton("Backward")){
		transform.Translate(-transform.forward * movespeed * Time.deltaTime);
	}
	if(Input.GetButton("Right")){
		transform.Rotate(transform.up * turnspeed * Time.deltaTime);
	}
	if(Input.GetButton("Left")){
		transform.Rotate(-transform.up * turnspeed * Time.deltaTime);
	}
	
	if(Input.GetButton("Jump")){
		transform.rigidbody.AddForce(Vector3.up * jumpforce);
	}
	
	if(Input.GetButton("Fire1")){
		Instantiate(myBullet , myMuzzle.position ,myMuzzle.rotation);
	}
}