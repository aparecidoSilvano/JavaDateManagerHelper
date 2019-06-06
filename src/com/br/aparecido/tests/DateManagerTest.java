package com.br.aparecido.tests;

class DateManagerTest {

	/*
	 * @Test public void testInstantiatingDateManager() { DateManager dateManager =
	 * new DateManager() {
	 * 
	 * @Override public void goToPreviousDate() { }
	 * 
	 * @Override public void goToNextDate() { }
	 * 
	 * @Override public boolean canGoToPreviousDate() { return false; }
	 * 
	 * @Override public boolean canGoToNextDate() { return false; }
	 * 
	 * @Override public void setSelectedDate(Calendar finalDate) throws
	 * DateManagerException { } };
	 * 
	 * Calendar today = Calendar.getInstance();
	 * 
	 * today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
	 * today.set(Calendar.HOUR_OF_DAY, 0); today.clear(Calendar.MILLISECOND);
	 * 
	 * assertEquals(dateManager.getStartDate().getTime(), today.getTime());
	 * assertEquals(dateManager.getFinalDate().getTime(), today.getTime());
	 * 
	 * Calendar minSelectableDate = Calendar.getInstance();
	 * 
	 * minSelectableDate.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
	 * minSelectableDate.clear(Calendar.MILLISECOND);
	 * 
	 * assertEquals(dateManager.getMinDateSelectable().getTime(),
	 * minSelectableDate.getTime()); }
	 * 
	 * @Test public void testWeeklyDataManager() { DateManager dateManager = new
	 * WeeklyDateManager();
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * Calendar today = Calendar.getInstance(); today.clear(Calendar.MINUTE);
	 * today.clear(Calendar.SECOND); today.set(Calendar.HOUR_OF_DAY, 0);
	 * today.clear(Calendar.MILLISECOND);
	 * 
	 * assertTrue(dateManager.getFinalDate().compareTo(today) == 0);
	 * assertFalse(dateManager.getStartDate().compareTo(today) == 0);
	 * 
	 * assertEquals(today.getTime(), dateManager.getFinalDate().getTime());
	 * 
	 * Calendar startDate = Calendar.getInstance();
	 * startDate.setTime(today.getTime()); startDate.add(Calendar.DAY_OF_YEAR, -6);
	 * 
	 * assertEquals(today.getTime(), dateManager.getFinalDate().getTime());
	 * assertEquals(startDate.getTime(), dateManager.getStartDate().getTime());
	 * 
	 * Calendar minSelectableDate = Calendar.getInstance();
	 * minSelectableDate.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
	 * minSelectableDate.clear(Calendar.MILLISECOND);
	 * 
	 * try {
	 * 
	 * dateManager.setSelectedDate(minSelectableDate); } catch (DateManagerException
	 * e) { fail(); }
	 * 
	 * minSelectableDate.set(1899, Calendar.DECEMBER, 31);
	 * 
	 * try { dateManager.setSelectedDate(minSelectableDate); fail();
	 * 
	 * } catch (DateManagerException e) { assertEquals("Data Inv�lida " +
	 * minSelectableDate.getTime().toString(), e.getMessage()); }
	 * 
	 * // Verifying if today is allowed
	 * 
	 * try {
	 * 
	 * dateManager.setSelectedDate(today);
	 * 
	 * } catch (DateManagerException e) { fail(); }
	 * 
	 * today.add(Calendar.DAY_OF_YEAR, 1);
	 * 
	 * try {
	 * 
	 * dateManager.setSelectedDate(today); fail();
	 * 
	 * } catch (DateManagerException e) { assertEquals("Data Inv�lida " +
	 * today.getTime().toString(), e.getMessage()); } }
	 * 
	 * @Test public void testWeeklyGoToNextDate() { DateManager dateManager = new
	 * WeeklyDateManager();
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * 
	 * Calendar dayBeforeYesterday = Calendar.getInstance();
	 * dayBeforeYesterday.clear(Calendar.MINUTE);
	 * dayBeforeYesterday.clear(Calendar.SECOND);
	 * dayBeforeYesterday.set(Calendar.HOUR_OF_DAY, 0);
	 * dayBeforeYesterday.clear(Calendar.MILLISECOND);
	 * 
	 * dayBeforeYesterday.add(Calendar.DAY_OF_YEAR, -2);
	 * 
	 * try { dateManager.setSelectedDate(dayBeforeYesterday); } catch
	 * (DateManagerException e) { fail(); }
	 * 
	 * try { dateManager.goToNextDate(); dateManager.goToNextDate(); } catch
	 * (DateManagerException e) { fail(); }
	 * 
	 * try { dateManager.goToNextDate(); fail(); } catch (DateManagerException e) {
	 * assertEquals("N�o � poss�vel avan�ar para uma data al�m de hoje.",
	 * e.getMessage()); }
	 * 
	 * Calendar minSelectableDate = Calendar.getInstance();
	 * minSelectableDate.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
	 * minSelectableDate.clear(Calendar.MILLISECOND);
	 * 
	 * try { dateManager.setSelectedDate(minSelectableDate); } catch
	 * (DateManagerException e) { fail(); }
	 * 
	 * try { dateManager.goToNextDate(); } catch (DateManagerException e) { fail();
	 * } }
	 * 
	 * @Test public void testWeeklyGoToPreviousDate() { DateManager dateManager =
	 * new WeeklyDateManager();
	 * 
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToPreviousDate(); } catch (DateManagerException e) {
	 * fail(); }
	 * 
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * Calendar minSelectableDate = Calendar.getInstance();
	 * minSelectableDate.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
	 * minSelectableDate.clear(Calendar.MILLISECOND);
	 * 
	 * try { dateManager.setSelectedDate(minSelectableDate); } catch
	 * (DateManagerException e) { fail(); }
	 * 
	 * assertFalse(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToPreviousDate(); fail(); } catch (DateManagerException
	 * e) {
	 * assertEquals("N�o � poss�vel regredir para uma data anterior a 01/01/1900.",
	 * e.getMessage()); } }
	 * 
	 * @Test public void testMonthlyDateManager() { DateManager dateManager = new
	 * MonthlyDateManager();
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * Calendar startDate = Calendar.getInstance();
	 * startDate.clear(Calendar.MINUTE); startDate.clear(Calendar.SECOND);
	 * startDate.clear(Calendar.MILLISECOND); startDate.set(Calendar.HOUR_OF_DAY,
	 * 0); startDate.set(Calendar.DAY_OF_MONTH, 1);
	 * 
	 * assertTrue(dateManager.getStartDate().compareTo(startDate) == 0);
	 * assertFalse(dateManager.getFinalDate().compareTo(startDate) == 0);
	 * 
	 * Calendar finalDate = Calendar.getInstance();
	 * finalDate.clear(Calendar.MINUTE); finalDate.clear(Calendar.SECOND);
	 * finalDate.set(Calendar.HOUR_OF_DAY, 0);
	 * finalDate.clear(Calendar.MILLISECOND); finalDate.set(Calendar.DAY_OF_MONTH,
	 * Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
	 * 
	 * assertEquals(finalDate.getTime(), dateManager.getFinalDate().getTime());
	 * assertEquals(startDate.getTime(), dateManager.getStartDate().getTime());
	 * 
	 * startDate.add(Calendar.MONTH, 1); finalDate.add(Calendar.MONTH, 1);
	 * 
	 * try { dateManager.setSelectedDate(finalDate); fail();
	 * 
	 * } catch (DateManagerException e) { assertEquals("Data Inv�lida " +
	 * finalDate.getTime().toString(), e.getMessage()); }
	 * 
	 * startDate.add(Calendar.MONTH, -3); finalDate.add(Calendar.MONTH, -3);
	 * finalDate.set(Calendar.DAY_OF_MONTH,
	 * finalDate.getActualMaximum(Calendar.DAY_OF_MONTH));
	 * 
	 * try { dateManager.setSelectedDate(finalDate);
	 * 
	 * assertEquals(finalDate.getTime(), dateManager.getFinalDate().getTime());
	 * assertEquals(startDate.getTime(), dateManager.getStartDate().getTime());
	 * 
	 * } catch (DateManagerException e) { fail(); }
	 * 
	 * Calendar date = Calendar.getInstance(); date.clear(Calendar.MINUTE);
	 * date.clear(Calendar.SECOND); date.set(Calendar.HOUR_OF_DAY, 0);
	 * date.clear(Calendar.MILLISECOND);
	 * 
	 * date.set(1899, 11, 31);
	 * 
	 * try {
	 * 
	 * dateManager.setSelectedDate(date); fail();
	 * 
	 * } catch (DateManagerException e) { assertEquals("Data Inv�lida " +
	 * date.getTime().toString(), e.getMessage()); }
	 * 
	 * assertEquals(finalDate.getTime(), dateManager.getFinalDate().getTime());
	 * assertEquals(startDate.getTime(), dateManager.getStartDate().getTime());
	 * 
	 * date.add(Calendar.MONTH, 1); date.set(Calendar.DAY_OF_MONTH, 1);
	 * 
	 * try { Calendar dateF = Calendar.getInstance(); dateF.setTime(date.getTime());
	 * dateF.set(Calendar.DAY_OF_MONTH, 31);
	 * 
	 * dateManager.setSelectedDate(date);
	 * 
	 * assertEquals(dateF.getTime(), dateManager.getFinalDate().getTime());
	 * assertEquals(date.getTime(), dateManager.getStartDate().getTime());
	 * 
	 * } catch (DateManagerException e) { fail(); } }
	 * 
	 * @Test public void testMonthlyGoToNextDate() { DateManager dateManager = new
	 * MonthlyDateManager();
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToPreviousDate();
	 * 
	 * assertTrue(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * } catch (DateManagerException e) { fail(); }
	 * 
	 * try { dateManager.goToNextDate(); } catch (DateManagerException e) { fail();
	 * }
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToNextDate(); fail(); } catch (DateManagerException e) {
	 * assertEquals("N�o � poss�vel avan�ar para uma data al�m de hoje.",
	 * e.getMessage()); }
	 * 
	 * Calendar date = Calendar.getInstance(); date.set(1900, 0, 31);
	 * 
	 * date.clear(Calendar.MINUTE); date.clear(Calendar.SECOND);
	 * date.set(Calendar.HOUR_OF_DAY, 0); date.clear(Calendar.MILLISECOND);
	 * 
	 * try { dateManager.setSelectedDate(date); } catch (DateManagerException e) {
	 * fail(); }
	 * 
	 * assertTrue(dateManager.canGoToNextDate());
	 * assertFalse(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToPreviousDate(); fail(); } catch (DateManagerException
	 * e) {
	 * assertEquals("N�o � poss�vel regredir para uma data anterior a 01/01/1900.",
	 * e.getMessage()); } }
	 * 
	 * @Test public void testYearlyDateManager() { DateManager dateManager = new
	 * YearlyDateManager();
	 * 
	 * assertFalse(dateManager.canGoToNextDate());
	 * assertTrue(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToNextDate(); fail(); } catch (DateManagerException e) {
	 * assertEquals("N�o � poss�vel avan�ar para uma data al�m de hoje.",
	 * e.getMessage()); }
	 * 
	 * Calendar date = Calendar.getInstance(); date.set(1900, 0, 31);
	 * 
	 * try { dateManager.setSelectedDate(date); } catch (DateManagerException e) {
	 * fail(); }
	 * 
	 * assertTrue(dateManager.canGoToNextDate());
	 * assertFalse(dateManager.canGoToPreviousDate());
	 * 
	 * try { dateManager.goToPreviousDate(); fail(); } catch (DateManagerException
	 * e) {
	 * assertEquals("N�o � poss�vel regredir para uma data anterior a 01/01/1900.",
	 * e.getMessage()); }
	 * 
	 * date.set(1899, 11, 31);
	 * 
	 * try { dateManager.setSelectedDate(date); fail(); } catch
	 * (DateManagerException e) { assertEquals("Data Inv�lida " +
	 * date.getTime().toString(), e.getMessage()); }
	 * 
	 * try { dateManager.goToNextDate(); dateManager.goToNextDate(); } catch
	 * (DateManagerException e) { fail(); }
	 * 
	 * System.out.println("testYearlyDateManager:");
	 * System.out.println(String.format("startDate = %d/%d/%d - %d:%d:%d",
	 * dateManager.getStartDate().get(Calendar.DAY_OF_MONTH),
	 * dateManager.getStartDate().get(Calendar.MONTH),
	 * dateManager.getStartDate().get(Calendar.YEAR),
	 * dateManager.getStartDate().get(Calendar.HOUR),
	 * dateManager.getStartDate().get(Calendar.MINUTE),
	 * dateManager.getStartDate().get(Calendar.SECOND)));
	 * 
	 * System.out.println(String.format("finalDate = %d/%d/%d - %d:%d:%d",
	 * dateManager.getFinalDate().get(Calendar.DAY_OF_MONTH),
	 * dateManager.getFinalDate().get(Calendar.MONTH),
	 * dateManager.getFinalDate().get(Calendar.YEAR),
	 * dateManager.getFinalDate().get(Calendar.HOUR),
	 * dateManager.getFinalDate().get(Calendar.MINUTE),
	 * dateManager.getFinalDate().get(Calendar.SECOND))); }
	 */
}
