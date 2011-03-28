package org.windycitygo.windycitygo.model;

public class Session {

	public String title;
	public Speaker speaker;
	public String description;
	public String startTime;
	public String endTime;
	public Links links;
	
	@Override
	public String toString() {
		return title;
	}
	
	/*
	 <session>
      <title>Keynote: Great Apps Need Customers</title>
      <speaker>
        <name>Brett Keintz</name>
        <company>Groupon</company>
        <bio>Brett Keintz is a product manager at Groupon with the official title of “Director of Social”. Prior to Groupon, Brett was CEO and co-founder of Sharethrough, a startup providing viral media distribution for major brand advertisers. Brett received an MBA from Stanford Business School in 2008 and holds a BS in Finance from Boston College. He’s originally from Madison, WI and is a huge fan of the Superbowl champion Packers.</bio>
 <headshot>http://windycitygo.org/assets/4d892a58dabe9d0f1c00001e/keintz.jpg</headshot>        
     </speaker>
      <description>Your team has built a great app.  Now, how do you get customers to install it, come back to it, and pay you money for it?

Brett Keintz has experience with the &quot;good problem to have&quot; of scaling a web app from zero to 20 million users in just 60 days. Whether it's on the web or on mobile the lessons learned are the same. Hear how to quickly get users, figure out what's not working, and optimize your product to the top.  
</description>
      <start_time>9:00am</start_time>
      <end_time>9:45am</end_time>
      <links>
            <speaker_website></speaker_website>
            <speaker_twitter>http://twitter.com/keintzb</speaker_twitter>
            <slides_url></slides_url>
            <rate_url></rate_url>
            <video_url></video_url>
      </links>
    </session>
    
	 */
}
