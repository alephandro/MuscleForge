# mysqldump -u root --databases MuscleForge > backup.sql
# mysql -u root MuscleForge < backup.sql
# mysql -u root < backup.sql

DROP SCHEMA IF EXISTS MuscleForge;
CREATE SCHEMA MuscleForge;

USE MuscleForge;

CREATE TABLE users (
					   email varchar(100) PRIMARY KEY,
					   password varchar(100)
);

CREATE TABLE exercises (
						   name varchar(50) PRIMARY KEY,
						   muscle_group varchar(50),
						   description varchar(300)
);
CREATE INDEX idx_muscle_group ON exercises (muscle_group);

INSERT INTO exercises
VALUES

	("Bench Press", "Chest", "Lying face up on a bench, lower the barbell to your chest and push it up. Works the chest."),
	("Incline Bench Press", "Chest", "Like the Bench Press, but with an incline. Works the upper chest."),
	("Decline Bench Press", "Chest", "Like the Bench Press, but with a decline. Works the lower chest."),
	("Push Up", "Chest", "Lie face down on the ground and push yourself up with your arms. Works the chest."),
	("Dumbbell Fly", "Chest", "Lying face up on a bench, hold the dumbbells in a cross position and bring them together in a hug-like motion. Works the chest."),
	("Dumbbell Bench Press", "Chest", "Like the Bench Press, but with dumbbels. Works the chest."),
	("Incline Dumbbell Bench Press", "Chest", "Like the Dumbbell Bench Press, but with an incline. Works the upper chest."),
	("Decline Dumbbell Bench Press", "Chest", "Like the Dumbbell Bench Press, but with a decline. Works the lower chest."),
	("Weighted Dip", "Chest", "In parallel bars or rings, lower yourself down and push yourself up. Works the chest."),

	("Back Squat", "Legs", "With a barbell on your back, squat down and get up. Works the quads."),
	("Front Squat", "Legs", "With a barbell on your clavicles, squat down and get up. Works the quads."),
	("Hack Squat", "Legs", "Using a Hack Squat machine, squat down and get up. Works the quads."),
	("Leg Press", "Legs", "Using a Leg Press machine, press the weight with your legs. Works the quads."),
	("Lunge", "Legs", "With a barbell or dumbbells, lunge forward with one leg and get up. Works the quads."),
	("Leg Extension", "Legs", "Using a Leg Extension machine, extend your legs and \"kick\" the weight up. Works the quads."),
	("Leg Curl", "Legs", "Using a Leg Curl machine, curl your legs. Works the hamstrings."),
	("Romanian Deadlift", "Legs", "With a very slight bend in your knees, pick up a barbell from the ground and lower it again. Works the hamstrings."),
	("Hip Thrust", "Legs", "Lying face up with a barbell on your hip and your back on a support, thrust your hip up. Works the glutes."),

	("Deadlift", "Back", "Standing up, squat down and pick up the barbell from the floor using your hands. Works the lower back and the spinal erectors."),
	("Back Extension", "Back", "Using a Back Extension bench, lower your upper body down and then extend your back. Works the lower back."),
	("Barbell Shrug", "Back", "Standing up with a barbell on your hands more than shoulder width apart, do a shrug movement. Works the traps."),
	("Upright Row", "Back", "Standing up with a barbell on your hands shoulder width apart, bring the barbell to your chin. Works the traps."),
	("Bent Over Row", "Back", "Standing up and bent over, row the barbell. Works the upper back and lats."),
	("Dumbbell Row", "Back", "Supported by a bench, row the dumbbell. Works the upper back and lats."),
	("Cable Row", "Back", "Sitting down on a cable machine, row the bar. Can be used with a variety of grips. Works the upper back."),
	("Lat Pulldown", "Back", "Sitting down on a vertical cable machine, pull the bar toward you. Can be used with a variety of grips. Works the and lats."),
	("Pullover", "Back", "Using a weight or a vertical cable machine, pull the weight over. Works the lats."),
	("Pull Up", "Back", "Using a pronated grip, pull yourself up toward the bar. Works the upper back and lats."),
	("Chin Up", "Back", "Using a supinated grip, pull yourself up toward the bar. Works the upper back, lats, and biceps."),

	("SkullCrusher", "Triceps", "Lying face up on a bench, hold a bar over your head. Bend your elbows and push the weight up again. Works the triceps."),
	("French Press", "Triceps", "Sitting up on a bench, hold a bar over your head. Bend your elbows and push the weight up again. Works the triceps."),
	("Triceps Dip", "Triceps", "In parallel bars or rings, lower yourself down and push yourself up. Works the triceps."),
	("Triceps Kickback", "Triceps", "Supported by a bench, kick the dumbbell or cable back. Works the triceps."),
	("Triceps Extension", "Triceps", "Using a cable machine, hold the bar over your head and extend your triceps upwards. Works the triceps."),
	("Triceps Pushdown", "Triceps", "Using a cable machine, hold the bar in front of you and extend your triceps downwards. Works the triceps."),

	("Barbell Curl", "Biceps", "Gripping a barbell with a supinated grip, curl your arms. Works the biceps."),
	("EZ Bar Curl", "Biceps", "Gripping an EZ bar with a supinated grip, curl your arms. Works the biceps."),
	("Dumbbell Curl", "Biceps", "Gripping dumbbels in your hands, curl your arms. Works the biceps."),
	("Cable Curl", "Biceps", "Gripping a cable bar with a supinated grip, curl your arms. Works the biceps."),
	("Reverse Barbell Curl", "Biceps", "Gripping a barbell with a pronated grip, curl your arms. Works the brachialis and brachioradialis."),
	("Reverse EZ Barr Curl", "Biceps", "Gripping an EZ bar with a pronated grip, curl your arms. Works the brachialis and brachioradialis."),
	("Reverse Dumbbell Curl", "Biceps", "Gripping dumbbels in your hands, curl your arms leading with your fists. Works the brachialis and brachioradialis."),
	("Reverse Cable Curl", "Biceps", "Gripping a cable bar with a pronated grip, curl your arms. Works the biceps."),
	("Preacher Curl", "Biceps", "Using a preacher bench, hold a barbell or EZ bar and curl it. Works the biceps."),
	("Hammer Curl", "Biceps", "Gropping dumbbells with a neutral grip, curl your arms. Works the biceps, brachialis, and brachioradialis"),

	("Military Press", "Shoulders", "With a barbell on your clavicles, push it up above your head with your arms. Works the front delts."),
	("Push Press", "Shoulders", "Like a Military Press, but with momentum. Works the front delts."),
	("Overhead Dumbbell Press", "Shoulders", "Like a Military Press but with dumbbells. Works the front delts."),
	("Lateral Raise", "Shoulders", "Gripping dumbbels with your hands at the side of your body, raise them until they're above your clavicle line and lower. Works the side delts."),
	("Lu Raise", "Shoulders", "Gripping weight with your hands at the side of your body, raise unntil they're above your head. Works the side delts and traps."),
	("Front Raise", "Shoulders", "Holding a barbell or dumbbels in front of you, raise them until they're at eye level. Works the front delts."),
	("Reverse Fly", "Shoulders", "Bent over with dumbbels on your hands, raise them sideways."),
	("Face Pull", "Shoulders", "Using a rope atatched to a cable machine, hold it in front of you and row it back until your elbows are at the side of your head. Works the posterior delts."),

	("Crunch", "Core", "Lying face up on the floor with your knees bent, crunch up using your abs. Works the abs."),
	("Plank", "Core", "Hold yourself in a push up position with your elbows. Works the whole core."),

	("Running", "Cardio", "Self explanatory."),
	("Cycling", "Cardio", "Self explanatory.")
