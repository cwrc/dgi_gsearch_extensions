package ca.discoverygarden.gsearch_extensions;

import junit.framework.TestCase;

public class JodaAdapterTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
		JodaAdapter.resetParsers();
	}

	public void testMDY() {
		String source = "07/22/2013";
		String dest = "2013-07-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
	public void testMDYHm() {
		String source = "07/22/2013 11:00";
		String dest = "2013-07-22T11:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testDMY() {
		String source = "22 April 2013";
		String dest = "2013-04-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testDMY_abv() {
		String source = "22 Apr 2013";
		String dest = "2013-04-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
	
	public void testDMY_French() {
		String source = "22 avril 2013";
		String dest = "2013-04-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testDMY_French_abv() {
		String source = "22 févr. 2013";
		String dest = "2013-02-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
  public void testMY() {
		String source = "February 2013";
		String dest = "2013-02-01T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

  public void testMY_abv() {
		String source = "Feb 2013";
		String dest = "2013-02-01T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testMY_French() {
		String source = "février 2013";
		String dest = "2013-02-01T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testMY_French_abv() {
		String source = "févr. 2013";
		String dest = "2013-02-01T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
 

	public void testMDYComma() {
		String source = "April 22, 2013";
		String dest = "2013-04-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testMDYComma_french() {
		String source = "avril 22, 2013";
		String dest = "2013-04-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testISO_BCE() {
		String source = "-9-10";
		String dest = "-0009-10-01T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testISODate() {
		String source = "2013-07-22";
		String dest = "2013-07-22T00:00:00.000Z";
		
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
	public void testISODateTime() {
		String source = "2013-07-22T00:00Z";
		String dest = "2013-07-22T00:00:00.000Z";

		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
	public void testISODateTimeWithOffset() {
		String source = "2013-07-22T04:00+04:00";
		String dest = "2013-07-22T00:00:00.000Z";

		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}

	public void testUnparsable() {
		assertEquals("", JodaAdapter.transformForSolr("2222-22-22"));
	}
	
	public void testAddFormat() {
		String source = "00™2013™22™07™+00:00™00";
		String format = "H™y™d™M™ZZ™m";
		String dest = "2013-07-22T00:00:00.000Z";
		
		assertEquals("", JodaAdapter.transformForSolr(source));
		JodaAdapter.addDateParser(format);
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
	
	public void testAddFormatAtPosition() {
		String source = "00™2013™22™07™+00:00™00";
		String format = "H™y™d™M™ZZ™m";
		String dest = "2013-07-22T00:00:00.000Z";
		
		assertEquals("", JodaAdapter.transformForSolr(source));
		JodaAdapter.addDateParser(0, format);
		assertEquals(dest, JodaAdapter.transformForSolr(source));
	}
}
