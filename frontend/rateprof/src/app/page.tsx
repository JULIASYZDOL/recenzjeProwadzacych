'use client'
import { useState } from 'react';
import Link from 'next/link';

export default function Home() {
  const [searchQuery, setSearchQuery] = useState('');

  return (
    <div className="container">
      <h2><strong>RateProf</strong></h2>
      <input
        type="text"
        placeholder="Wyszukaj uczelnię..."
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
      />
      <Link href={`dashboard/${searchQuery}`}>
        <button>Szukaj!</button>
      </Link>
    </div>
  );
}


